package com.project.dasarang.global.infra.education.service;

import com.project.dasarang.global.config.AppProperties;
import com.project.dasarang.global.config.WebClientConfiguration;
import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.Tuition;
import com.project.dasarang.global.infra.education.domain.repository.EducationRepository;
import com.project.dasarang.global.infra.education.domain.repository.TuitionRepository;
import com.project.dasarang.global.infra.education.dto.RowDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EducationApiService {

    private final EducationRepository educationRepository;
    private final TuitionRepository tuitionRepository;
    private final WebClientConfiguration webClient;
    private final AppProperties appProperties;

    @Async
    @Transactional
    public void execute(String code) throws JSONException {
        String response = getResponse(code).block();

        JSONArray data = new JSONObject(response)
                .getJSONArray("acaInsTiInfo")
                .getJSONObject(1)
                .getJSONArray("row");
        List<RowDto> dtoList = jsonArrayToList(data);

        for(RowDto item : dtoList) {
            boolean checkTuition = item.getPSNBY_THCC_CNTNT().equals("null");
            if (educationRepository.existsByAcaAsnum(item.getACA_ASNUM())) {
                Education education = educationRepository.findByAcaAsnum(item.getACA_ASNUM()).get();
                if(!checkTuition) education.addTuition(getTuitionList(item.getPSNBY_THCC_CNTNT()));
                education.updateEducation(item.getADMST_ZONE_NM(), item.getACA_NM(),
                        item.getESTBL_YMD(), item.getREG_STTUS_NM(),
                        item.getTOFOR_SMTOT(), item.getLE_CRSE_NM(),
                        item.getFA_RDNMA());
                educationRepository.save(education);
            } else {
                Education education = item.toEntity();
                if(!checkTuition) education.addTuition(getTuitionList(item.getPSNBY_THCC_CNTNT()));
                educationRepository.save(education);
            }
        }

        log.info("Update Finish");
    }

    public Mono<String> getResponse(String code) {
        return webClient.apiClient()
                .get()
                .uri(uri(code).toUriString())
                .retrieve()
                .bodyToMono(String.class);
    }

    public List<RowDto> jsonArrayToList(JSONArray array) throws JSONException {
        List<RowDto> data = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            data.add(jsonToDto(array.getJSONObject(i)));
        }
        return data;
    }

    public List<Tuition> getTuitionList(String item) {
        String[] tuitions = item.split("/ ");
        return Arrays.stream(tuitions).map(tuition -> {
            String title = tuition.split(":")[0];
            int price = Integer.parseInt(tuition.split(":")[1]);
            if(tuitionRepository.existsByTitle(title))
                tuitionRepository.deleteAllByTitle(title);
            return Tuition.builder()
                    .title(title)
                    .price(price)
                    .build();
        }).collect(Collectors.toList());
    }

    public RowDto jsonToDto(JSONObject data) throws JSONException {
        return RowDto.builder()
                .ADMST_ZONE_NM(data.get("ADMST_ZONE_NM").toString())
                .ACA_NM(data.get("ACA_NM").toString())
                .ESTBL_YMD(data.get("ESTBL_YMD").toString())
                .REG_STTUS_NM(data.get("REG_STTUS_NM").toString())
                .TOFOR_SMTOT((Integer) data.get("TOFOR_SMTOT"))
                .LE_CRSE_NM(data.get("LE_CRSE_NM").toString())
                .PSNBY_THCC_CNTNT(data.get("PSNBY_THCC_CNTNT").toString())
                .FA_RDNMA(data.get("FA_RDNMA").toString())
                .ACA_ASNUM(data.get("ACA_ASNUM").toString())
                .build();
    }

    public UriComponents uri(String code) {
        return UriComponentsBuilder.newInstance()
                .path("/hub/acaInsTiInfo")
                .queryParam("KEY", appProperties.getApiKey())
                .queryParam("Type", appProperties.getType())
                .queryParam("ATPT_OFCDC_SC_CODE", code)
                .build();
    }

}

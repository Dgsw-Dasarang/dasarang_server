package com.project.dasarang.global.infra.education.dto;

import com.project.dasarang.global.infra.education.domain.Education;
import lombok.*;

import java.io.Serializable;

@Getter @Builder
@AllArgsConstructor
public class RowDto implements Serializable {

    private String ADMST_ZONE_NM;
    private String ACA_NM;
    private String ESTBL_YMD;
    private String REG_STTUS_NM;
    private int TOFOR_SMTOT;
    private String LE_CRSE_LIST_NM;
    private String PSNBY_THCC_CNTNT;
    private String FA_RDNMA;

    public Education toEntity() {
        return Education.builder()
                .admstZoneName(this.ADMST_ZONE_NM)
                .academyName(this.ACA_NM)
                .createdAt(this.ESTBL_YMD)
                .status(this.REG_STTUS_NM)
                .totalSeats(this.TOFOR_SMTOT)
                .courseListName(this.LE_CRSE_LIST_NM)
                .roadAddress(this.FA_RDNMA)
                .build();
    }

}

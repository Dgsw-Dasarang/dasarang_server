package com.project.dasarang.global.infra.education.ro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class EducationResponse {

    private String ADMST_ZONE_NM;
    private String ACA_NM;
    private String ESTBL_YMD;
    private String REG_STTUS_NM;
    private String TOFOR_SMTOT;
    private String LE_CRSE_LIST_NM;
    private String PSNBY_TNCC_CNTNT;
    private String FA_RDNMA;

}

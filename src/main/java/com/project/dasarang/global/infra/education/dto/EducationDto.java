package com.project.dasarang.global.infra.education.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EducationDto implements Serializable {

    private String ADMST_ZONE_NM;
    private String ACA_NM;
    private String ESTBL_YMD;
    private String REG_STTUS_NM;
    private String TOFOR_SMTOT;
    private String LE_CRSE_LIST_NM;
    private String PSNBY_TNCC_CNTNT;
    private String FA_RDNMA;

}

package com.project.dasarang.global.infra.education.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class ApiDto implements Serializable {

    @JsonProperty("acaInsTiInfo")
    private List<AcaInsTiInfoDto> acaInsTiInfo;

}

package com.project.dasarang.global.infra.education.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class AcaInsTiInfoDto implements Serializable {

    @JsonProperty("head")
    private Object head;

    @JsonProperty("row")
    private List<EducationDto> row;

}

package com.project.dasarang.domain.api.domain;

import com.project.dasarang.global.entity.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_key")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Key extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyId;

    @Column(unique = true)
    private String apiKey;

    private String name;
    private String number;
    private String reason;

    @Builder
    public Key(String key, String name, String number, String reason) {
        this.apiKey = key;
        this.name = name;
        this.number = number;
        this.reason = reason;
    }

}

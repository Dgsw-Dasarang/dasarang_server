package com.project.dasarang.domain.auth.domain;

import com.project.dasarang.domain.auth.domain.enums.UserStatus;
import com.project.dasarang.domain.auth.domain.enums.UserType;
import com.project.dasarang.global.entity.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerNumber;

    private String email;

    private String userId;

    private String password;

    private String address;

    private String number;

    private String birth;

    @Enumerated(EnumType.STRING)
    private UserType authority;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    public void updateUser(String userId, String address, String number, String birth) {
        this.userId = userId;
        this.address = address;
        this.number = number;
        this.birth = birth;
    }

    public void deactivateUser() {
        this.status = UserStatus.DEACTIVATED;
    }

    @Builder
    public User(String ownerNumber, String email, String userId, String password, String address, String number, String birth, UserType authority) {
        this.ownerNumber = ownerNumber;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.address = address;
        this.number = number;
        this.birth = birth;
        this.authority = authority;
    }
}
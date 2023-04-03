package com.project.dasarang.global.utils;

import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.presentation.dto.response.ImageResponse;
import com.project.dasarang.domain.post.presentation.dto.response.PostResponse;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.presentation.dto.response.UserResponse;
import com.project.dasarang.domain.education.presentation.dto.response.EducationResponse;
import com.project.dasarang.domain.education.presentation.dto.response.TuitionResponse;
import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.Tuition;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtil {

    public static UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId()).password(user.getPassword())
                .address(user.getAddress()).number(user.getNumber())
                .type(user.getAuthority().getRole()).address(user.getAddress())
                .ownerNumber(user.getOwnerNumber()).email(user.getEmail())
                .birth(user.getBirth())
                .build();
    }

    public static EducationResponse getEducationResponse(Education education, List<Tuition> tuitions) {
        return EducationResponse.builder()
                .admstZoneName(education.getAdmstZoneName())
                .academyName(education.getAcademyName())
                .createdAt(education.getCreatedAt())
                .status(education.getStatus())
                .totalSeats(education.getTotalSeats())
                .courseListName(education.getCourseListName())
                .tuitions(tuitions != null ? tuitions.stream().map(ResponseUtil::getTuitionResponse).collect(Collectors.toList()) : null)
                .roadAddress(education.getRoadAddress())
                .build();
    }

    public static TuitionResponse getTuitionResponse(Tuition tuition) {
        return TuitionResponse.builder()
                .title(tuition.getTitle())
                .price(tuition.getPrice())
                .build();
    }

    public static PostResponse getPostResponse(Post post, List<Image> images) {
        return PostResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .images(images != null ? images.stream().map(ResponseUtil::getImageResponse).collect(Collectors.toList()) : null)
                .build();
    }

    public static ImageResponse getImageResponse(Image image) {
        return ImageResponse.builder()
                .url(image.getUrl())
                .type(image.getType())
                .build();
    }

}

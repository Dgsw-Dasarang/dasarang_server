package com.project.dasarang.global.infra.education.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CronJob {

    private final EducationApiService educationService;

    @Scheduled(cron = "0 26 11 * * ?")
    public void updateEducation() throws JSONException {
        educationService.execute("D10");
    }

}

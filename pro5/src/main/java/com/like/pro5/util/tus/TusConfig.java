package com.like.pro5.util.tus;

import me.desair.tus.server.TusFileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TusConfig {

    @Value("${tus.server.data.directory}")
    String tusStoragePath;

    @Value("${tus.server.data.expiration}")
    Long tusExpirationPeriod;

    @Bean
    public TusFileUploadService tus() {
        return new TusFileUploadService()
                .withStoragePath(tusStoragePath)
                .withDownloadFeature()
                .withUploadExpirationPeriod(tusExpirationPeriod)
                .withUploadURI("/file/tus/upload");
    }
}
package com.like.pro5.util.tus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.desair.tus.server.TusFileUploadService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TusCleanUpScheduler {

    private final TusFileUploadService tus;

    @Scheduled(fixedDelay = 10000)
    public void cleanup() throws IOException {
        log.info("clean up");

        tus.cleanup();
    }
}
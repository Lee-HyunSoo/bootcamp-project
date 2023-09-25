package com.like.pro5.service;

import com.like.pro5.util.ffmpeg.FFmpegManager;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import com.like.pro5.util.tus.DurationExtractor;
import com.like.pro5.util.tus.ThumbnailExtractor;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import me.desair.tus.server.upload.UploadInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    private final TusFileUploadService tusFileUploadService;
//    private final FFmpegManager fFmpegManager;
    private final ThreadLocalLogTrace trace;

    @Value("${image.save.path}")
    private String savedImage;

    @Value("${tus.save.path}")
    private String savedPath;

    public void imageFileUpload(MultipartFile multipartFile) {
        TraceStatus status = trace.begin("FileService.imageFileUpload()");

        if (!multipartFile.isEmpty()) {
            Path filePath = Paths.get(savedImage, multipartFile.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(filePath)) {
                os.write(multipartFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        trace.end(status);
    }

    public Resource getImageResource(String filename) throws MalformedURLException {
        Path imagePath = Paths.get(savedImage).resolve(filename);
        Resource resource = new UrlResource(imagePath.toUri());
        return resource;
    }

    public String process(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Process a tus upload request
            tusFileUploadService.process(request, response);

            // Get upload information
            UploadInfo uploadInfo = tusFileUploadService.getUploadInfo(request.getRequestURI());

            if (uploadInfo != null && !uploadInfo.isUploadInProgress()) {
                // Progress status is successful: Create file
                createFile(tusFileUploadService.getUploadedBytes(request.getRequestURI()), uploadInfo.getFileName());

                // Delete an upload associated with the given upload url
                tusFileUploadService.deleteUpload(request.getRequestURI());
            }
            return null;
        } catch (IOException | TusException e) {
            log.error("exception was occurred. message={}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private void createFile(InputStream is, String filename) throws IOException {
        File file = new File(savedPath, filename);

        FileUtils.copyInputStreamToFile(is, file);

        // 썸네일 추출
        ThumbnailExtractor.extract(file);
//        fFmpegManager.getThumbnail(file.getAbsolutePath());
        // 영상 길이 추출
        DurationExtractor.extract(file);
//        fFmpegManager.getDuration(file.getAbsolutePath());
    }

}

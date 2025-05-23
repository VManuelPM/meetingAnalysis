package com.amoelcodigo.meetinganalysis.service;

import com.amoelcodigo.meetinganalysis.dto.MeetingAnalysisResponse;
import com.amoelcodigo.meetinganalysis.service.AudioExtractorService;
import com.amoelcodigo.meetinganalysis.service.AudioTranscriptionService;
import com.amoelcodigo.meetinganalysis.service.MeetingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
@AllArgsConstructor
public class MeetingServiceFile {

    private final AudioExtractorService audioExtractorService;
    private final AudioTranscriptionService audioTranscriptionService;
    private final MeetingService meetingService;

    public MeetingAnalysisResponse processFile(MultipartFile file) throws IOException, InterruptedException {
        log.info("processFile iniciado con archivo: {}", file.getOriginalFilename());

        File videoFile = null;
        File audioFile = null;

        try {
            videoFile = File.createTempFile("video", ".mp4");
            videoFile.deleteOnExit();
            file.transferTo(videoFile.toPath());

            audioFile = audioExtractorService.extractAudio(videoFile);

            String transcript = audioTranscriptionService.transcribeAudio(audioFile);

            return meetingService.analyzeMeeting(transcript);
        } finally {
            deleteFileIfExists(videoFile);
            deleteFileIfExists(audioFile);
        }
    }

    private void deleteFileIfExists(File file) {
        if (file != null && file.exists()) {
            if (file.delete()) {
                log.info("Archivo temporal borrado: {}", file.getAbsolutePath());
            } else {
                log.warn("No se pudo borrar archivo temporal: {}", file.getAbsolutePath());
            }
        }
    }
}

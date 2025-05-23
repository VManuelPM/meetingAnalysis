package com.amoelcodigo.meetinganalysis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Slf4j
public class AudioExtractorService {

    public File extractAudio(File videoFile) throws IOException, InterruptedException {
        File audioFile = File.createTempFile("audio", ".wav");
        audioFile.deleteOnExit();

        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg", "-y", "-i", videoFile.getAbsolutePath(),
                "-vn", "-acodec", "pcm_s16le", "-ar", "16000", "-ac", "1",
                audioFile.getAbsolutePath()
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();
        process.getOutputStream().close();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                log.info("[ffmpeg] {}", line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Error al extraer audio con ffmpeg. Código de salida: " + exitCode);
        }

        log.info("Audio extraído correctamente: {}", audioFile.getAbsolutePath());
        log.info("Tamaño archivo audio generado: {} bytes", audioFile.length());
        return audioFile;
    }
}

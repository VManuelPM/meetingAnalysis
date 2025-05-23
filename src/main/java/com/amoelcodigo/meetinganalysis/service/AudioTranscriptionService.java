package com.amoelcodigo.meetinganalysis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class AudioTranscriptionService {

    public String transcribeAudio(File audioFile) throws IOException, InterruptedException {
        File scriptFile = File.createTempFile("transcribe", ".py");
        scriptFile.deleteOnExit();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("scripts/transcribe.py")) {
            if (in == null) {
                throw new FileNotFoundException("No se encontró transcribe.py en resources/scripts/");
            }
            Files.copy(in, scriptFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        log.info("Ejecutando script Python de transcripción: {}", scriptFile.getAbsolutePath());

        ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/python3", scriptFile.getAbsolutePath(), audioFile.getAbsolutePath());
        pb.redirectErrorStream(true);
        Process process = pb.start();

        StringBuilder outputBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                log.info("[Python] {}", line);
                outputBuilder.append(line).append("\n");
            }
        }

        int exitCode = process.waitFor();
        String output = outputBuilder.toString().trim();

        if (exitCode != 0) {
            throw new RuntimeException("Whisper falló al transcribir el audio. Código de salida: " + exitCode + "\nSalida del script:\n" + output);
        }

        log.info("Transcripción obtenida:\n{}", output);

        return output;
    }
}

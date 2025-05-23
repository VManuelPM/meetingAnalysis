package com.amoelcodigo.meetinganalysis.service;

import com.amoelcodigo.meetinganalysis.dto.MeetingAnalysisResponse;
import com.amoelcodigo.meetinganalysis.prompt.PromptBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MeetingService {

    private final OllamaChatModel ollamaChatModel;
    private final PromptBuilder promptBuilder;
    private final ObjectMapper objectMapper;

    public MeetingAnalysisResponse analyzeMeeting(String transcript) {
        String prompt = promptBuilder.build(transcript);
        String response = ollamaChatModel.call(prompt);

        if(response == null || response.isBlank()) {
            throw new IllegalStateException("Respuesta Vacia del modelo", null);
        }

        try {
            return objectMapper.readValue(response, MeetingAnalysisResponse.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error al parsear JSON de la respuesta", e);
        }
    }
}

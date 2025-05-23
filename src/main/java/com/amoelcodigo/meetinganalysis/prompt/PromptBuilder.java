package com.amoelcodigo.meetinganalysis.prompt;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {
    public String build(String transcript){
        return """
                Eres un asistente experto en análisis de reuniones. Dada la siguiente transcripción,
                analiza y responde estrictamente con un JSON válido en el siguiente formato, **sin texto adicional ni explicaciones**:
                
                {
                  "summary": "Resumen breve",
                  "tasks": ["Tarea 1", "Tarea 2"],
                  "decisions": ["Decisión A", "Decisión B"],
                  "openQuestions": ["¿Qué pasa con X?", "¿Quién hará Y?"]
                }
                
                La transcripción es:
                %s
                
                Recuerda, responde únicamente con el JSON válido y nada más.
                """.formatted(transcript);
    }
}

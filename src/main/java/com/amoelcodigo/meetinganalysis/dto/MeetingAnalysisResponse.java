package com.amoelcodigo.meetinganalysis.dto;

import java.util.List;

public record MeetingAnalysisResponse(
        String summary,
        List<String> task,
        List<String> decisions,
        List<String> openQuestions
) {
}

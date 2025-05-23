package com.amoelcodigo.meetinganalysis.controller;

import com.amoelcodigo.meetinganalysis.dto.MeetingAnalysisRequest;
import com.amoelcodigo.meetinganalysis.dto.MeetingAnalysisResponse;
import com.amoelcodigo.meetinganalysis.service.MeetingService;
import com.amoelcodigo.meetinganalysis.service.MeetingServiceFile;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/meeting")
@AllArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;
    private final MeetingServiceFile meetingServiceFile;

    @PostMapping("/analyze")
    public ResponseEntity<MeetingAnalysisResponse> analyze(@RequestBody @Valid final MeetingAnalysisRequest request) {
        return ResponseEntity.ok(this.meetingService.analyzeMeeting(request.transcript()));
    }

    @PostMapping("/upload")
    public ResponseEntity<MeetingAnalysisResponse> analyzeVideo(@RequestParam("file") final MultipartFile file) throws IOException, InterruptedException {
        return ResponseEntity.ok(this.meetingServiceFile.processFile(file));
    }

}

package com.sergio.controller;

import com.sergio.converter.FeedbackConverter;
import com.sergio.domain.Feedback;
import com.sergio.dto.FeedbackDto;
import com.sergio.dto.HistoryDto;
import com.sergio.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FeedbackController {

    private FeedbackService feedbackService;
    private FeedbackConverter feedbackConverter;

    @Autowired
    public FeedbackController(FeedbackService feedbackService,
                              @Lazy FeedbackConverter feedbackConverter) {
        this.feedbackService = feedbackService;
        this.feedbackConverter = feedbackConverter;

    }

    @GetMapping(value = "/tickets/{id}/feedback")
    public ResponseEntity getTicketFeedback(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(feedbackConverter.toDtoList(feedbackService.getTicketFeedback(id, principal)));
    }


    @PostMapping(value = "/tickets/{id}/feedback")
    public ResponseEntity addFeedbackToTicket(@PathVariable Long id, @RequestBody FeedbackDto feedbackDto, Principal principal){
        System.out.println("---------------------------------");
        feedbackService.addFeedbackToTicket(id, feedbackConverter.fromDto(feedbackDto), principal);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}

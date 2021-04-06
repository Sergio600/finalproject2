package com.sergio.controller;

import com.sergio.converter.FeedbackConverter;
import com.sergio.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    FeedbackConverter feedbackConverter;

    @GetMapping(value = "/tickets/{id}/feedback")
    public ResponseEntity getTicketFeedback(@PathVariable int id, Principal principal){
        return ResponseEntity.ok(feedbackConverter.toDto(feedbackService.getTicketFeedback(id, principal)));
    }

}

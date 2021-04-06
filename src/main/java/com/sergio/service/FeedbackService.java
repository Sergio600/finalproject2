package com.sergio.service;

import com.sergio.domain.Feedback;
import com.sergio.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    public void createFeedbackToTicket(int id, Feedback feedback, Principal principal){

    }

    public Feedback getTicketFeedback(int id, Principal principal) {
        return feedbackRepository.getTicketFeedback(id);
    }
}

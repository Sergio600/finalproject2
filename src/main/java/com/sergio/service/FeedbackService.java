package com.sergio.service;

import com.sergio.domain.Feedback;
import com.sergio.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    public void createFeedbackToTicket(Long id, Feedback feedback, Principal principal){

    }

    public List<Feedback> getTicketFeedback(Long id, Principal principal) {
        return feedbackRepository.getTicketFeedback(id);
    }
}

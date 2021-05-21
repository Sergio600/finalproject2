package com.sergio.service;

import com.sergio.domain.Feedback;
import com.sergio.domain.History;
import com.sergio.domain.User;
import com.sergio.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.Ticket;

import java.security.Principal;
import java.util.List;

@Service
public class FeedbackService {

    private FeedbackRepository feedbackRepository;
    private TicketService ticketService;
    private UserService userService;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository,
                           TicketService ticketService,
                           UserService userService){
        this.feedbackRepository= feedbackRepository;
        this.ticketService = ticketService;
        this.userService = userService;
    }


    public List<Feedback> getTicketFeedback(Long id, Principal principal) {
        return feedbackRepository.getTicketFeedback(id);
    }

    public void addFeedbackToTicket(Long id, Feedback feedback, Principal principal) {
        feedback.setTicket(ticketService.getTicketById(id));
        feedback.setUser(userService.getCurrentUser(principal.getName()));
        feedbackRepository.saveFeedbackToTicket(feedback);
    }
}

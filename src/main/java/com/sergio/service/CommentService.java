package com.sergio.service;

import com.sergio.domain.Comment;
import com.sergio.domain.Ticket;
import com.sergio.domain.User;
import com.sergio.repository.CommentReposiroty;
import com.sergio.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Lazy
public class CommentService {

    private  CommentReposiroty commentReposiroty;
    private  UserService userService;
//    private  TicketService ticketService;
    private TicketRepository ticketRepository;

    @Autowired
    public CommentService(CommentReposiroty commentReposiroty,
                          UserService userService,
//                          TicketService ticketService,
                          TicketRepository ticketRepository){
        this.commentReposiroty = commentReposiroty;
        this.userService = userService;
//        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
    }

    public CommentService(){}


    public List<Comment> getTicketComments(Long id, Principal principal) {
        return commentReposiroty.getTicketComments(id);
    }

    public List<Comment> getAllComments() {
        return commentReposiroty.getAllComments();
    }

    public void addCommentToTicket(Long id, Comment comment, Principal principal) {
        User user = userService.getCurrentUser(principal.getName());
//        comment.setTicket(ticketService.getTicketById(id));
        comment.setTicket(ticketRepository.getById(id).get());
        comment.setUser(user);
        commentReposiroty.addCommentToTicket(comment);
    }

    public void createCommentToTicket(String text, User user, Ticket ticket) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setTicket(ticket);
        comment.setText(text);
        comment.setDate(new Timestamp(System.currentTimeMillis()));
        commentReposiroty.addCommentToTicket(comment);
    }
}

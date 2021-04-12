package com.sergio.service;

import com.sergio.domain.Comment;
import com.sergio.domain.User;
import com.sergio.repository.CommentReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CommentSevice {

    @Autowired
    CommentReposiroty commentReposiroty;

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    public List<Comment> getTicketComments(int id, Principal principal) {
        return commentReposiroty.getTicketComments(id);
    }

    public List<Comment> getAllComments() {
        return commentReposiroty.getAllComments();
    }

    public void addCommentToTicket(int id, Comment comment, Principal principal) {
        User user = userService.getCurrentUser(principal.getName());
        comment.setTicket(ticketService.getTicketById(id));
        comment.setUser(user);
        commentReposiroty.addCommentToTicket(comment);
    }
}

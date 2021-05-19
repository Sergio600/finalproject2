package com.sergio.controller;

import com.sergio.converter.CommentConverter;
import com.sergio.dto.CommentDto;
import com.sergio.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    private  CommentConverter commentConverter;
    private  CommentService commentService;

    @Autowired
    public CommentController(@Lazy CommentConverter commentConverter,
                             CommentService commentService){
        this.commentConverter = commentConverter;
        this.commentService = commentService;
    }

    @GetMapping(value = "/comments")
    public ResponseEntity getAllComments(){
        return ResponseEntity.ok(commentConverter.toDtoList(commentService.getAllComments()));
    }


    @GetMapping(value = "/tickets/{id}/comments")
    public ResponseEntity getTicketComments(@PathVariable Long id, Principal principal){
        return ResponseEntity.ok(commentConverter.toDtoList(commentService.getTicketComments(id, principal)));
    }

    @PostMapping(value = "/tickets/{id}/comments")
    public ResponseEntity addCommentToTicket(@PathVariable Long id, @RequestBody CommentDto commentDto, Principal principal){
        commentService.addCommentToTicket(id, commentConverter.fromDto(commentDto), principal);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

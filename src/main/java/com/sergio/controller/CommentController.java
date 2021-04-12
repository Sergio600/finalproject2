package com.sergio.controller;

import com.sergio.converter.CommentConverter;
import com.sergio.dto.CommentDto;
import com.sergio.service.CommentSevice;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentController {

    @Autowired
    CommentConverter commentConverter;

    @Autowired
    CommentSevice commentSevice;

    @GetMapping(value = "/comments")
    public ResponseEntity getAllComments(){
        return ResponseEntity.ok(commentConverter.toDtoList(commentSevice.getAllComments()));
    }


    @GetMapping(value = "/tickets/{id}/comments")
    public ResponseEntity getTicketComments(@PathVariable int id, Principal principal){
        return ResponseEntity.ok(commentConverter.toDtoList(commentSevice.getTicketComments(id, principal)));
    }

    @PostMapping(value = "/tickets/{id}/comments")
    public ResponseEntity addCommentToTicket(@PathVariable int id, @RequestBody CommentDto commentDto, Principal principal){
        commentSevice.addCommentToTicket(id, commentConverter.fromDto(commentDto), principal);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

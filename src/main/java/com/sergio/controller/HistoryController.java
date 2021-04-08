package com.sergio.controller;

import com.sergio.converter.HistoryConverter;
import com.sergio.dto.CommentDto;
import com.sergio.dto.HistoryDto;
import com.sergio.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class HistoryController {

    @Autowired
    HistoryConverter historyConverter;

    @Autowired
    HistoryService historyService;

    @GetMapping(value = "/histories")
    public ResponseEntity getAllComments(Principal principal){
        return ResponseEntity.ok(historyConverter.toDtoList(historyService.getAllHistories(principal)));
    }


    @GetMapping(value = "/tickets/{id}/history")
    public ResponseEntity getTicketHistory(@PathVariable int id, Principal principal){
        return ResponseEntity.ok(historyConverter.toDtoList(historyService.getTicketHistory(id, principal)));
    }

    @PostMapping(value = "/tickets/{id}/comments")
    public ResponseEntity addHistoryToTicket(@PathVariable int id, @RequestBody HistoryDto historyDto, Principal principal){
        historyService.addHistroryToTicket(id, historyConverter.fromDto(historyDto), principal);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}

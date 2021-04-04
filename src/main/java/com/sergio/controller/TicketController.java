package com.sergio.controller;

import com.sergio.dto.TicketDto;
import com.sergio.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public ResponseEntity getTickets(Principal principal){
        return ResponseEntity.ok(ticketService.getAllTickets(principal));
    }

    @PostMapping
    public ResponseEntity createTicket(@RequestBody TicketDto ticketDto, Principal principal){
        ticketService.createTicket(ticketDto, principal);
        return ResponseEntity.ok("Ticket is created");
    }

    @PutMapping(value = "/id")
    public ResponseEntity editTicket(@PathVariable int id, @RequestBody TicketDto ticketDto, Principal principal){
        ticketService.editTicket(ticketDto);
        return ResponseEntity.ok("Ticket is edit ");
    }

}

package com.sergio.controller;

import com.sergio.converter.TicketConverter;
import com.sergio.dto.TicketDto;
import com.sergio.enums.State;
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

    @Autowired
    TicketConverter ticketConverter;

    @GetMapping()
    public ResponseEntity getUserTickets(Principal principal) {
        return ResponseEntity.ok(ticketConverter.toDtoList(ticketService.getUserTickets(principal)));
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllTickets(Principal principal) {
        return ResponseEntity.ok(ticketConverter.toDtoList(ticketService.getAllTickets(principal)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getTicket(@PathVariable int id, Principal principal) {
        return ResponseEntity.ok(ticketConverter.toDto(ticketService.getTicketById(id)));
    }

    @PostMapping
    public ResponseEntity createTicket(@RequestBody TicketDto ticketDto, Principal principal) {
        ticketService.createTicket(ticketDto, principal, State.NEW);
        return ResponseEntity.ok("Ticket is created");
    }

    @PutMapping(value = "/id")
    public ResponseEntity editTicket(@PathVariable int id, @RequestBody TicketDto ticketDto, Principal principal) {
        ticketService.editTicket(id, ticketDto, principal, State.NEW);
        return ResponseEntity.ok("Ticket is edited ");
    }
}

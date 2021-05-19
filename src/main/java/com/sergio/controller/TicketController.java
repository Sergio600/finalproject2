package com.sergio.controller;

import com.sergio.converter.TicketConverter;
import com.sergio.dto.TicketDto;
import com.sergio.enums.State;
import com.sergio.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.security.Principal;

@RestController
@RequestMapping(value = "/tickets")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {

    private TicketService ticketService;
    private TicketConverter ticketConverter;

    @Autowired
    public TicketController(TicketService ticketService,
                            TicketConverter ticketConverter) {
        this.ticketService = ticketService;
        this.ticketConverter = ticketConverter;
    }

    @GetMapping()
    public ResponseEntity getUserTicketsByRole(Principal principal) {
        return ResponseEntity.ok(ticketConverter.toDtoList(ticketService.getUserTicketsByRole(principal)));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity getUserTicketsFilter(Principal principal) {
        return ResponseEntity.ok(ticketConverter.toDtoList(ticketService.getUserTicketsFilter(principal)));
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllTickets(Principal principal) {
        return ResponseEntity.ok(ticketConverter.toDtoList(ticketService.getAllTickets()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketConverter.toDto(ticketService.getTicketById(id)));
    }


    @PostMapping()
    public ResponseEntity createTicket(@RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
                                       @RequestParam(value = "ticketDto") String ticketDto,
                                       Principal principal) {
        System.out.println(ticketDto);
        ticketService.createTicket(ticketConverter.fromJson(ticketDto), principal, State.NEW);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @PostMapping(value = "/draft")
    public ResponseEntity createTicketDraft(@RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
                                            @RequestParam(value = "ticketDto") String ticketDto,
                                            Principal principal) {
        System.out.println(ticketDto);
        ticketService.createTicket(ticketConverter.fromJson(ticketDto), principal, State.DRAFT);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/id")
    public ResponseEntity editTicket(@PathVariable Long id, @RequestBody TicketDto ticketDto, Principal principal) {
        ticketService.editTicket(id, ticketConverter.fromDto(ticketDto), principal, State.NEW);
        return ResponseEntity.ok("Ticket is edited ");
    }

    @PutMapping(value = "/{id}/draft")
    public ResponseEntity editTicketDraft(@PathVariable Long id, @RequestBody TicketDto ticketDto, Principal principal) {
        ticketService.editTicket(id, ticketConverter.fromDto(ticketDto), principal, State.DRAFT);
        return ResponseEntity.ok("Ticket draft is edited");
    }
}

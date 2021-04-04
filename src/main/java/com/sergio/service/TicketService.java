package com.sergio.service;

import com.sergio.converter.TicketConverter;
import com.sergio.dto.TicketDto;
import com.sergio.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketConverter ticketConverter;

    public List<TicketDto> getAllTickets(Principal principal) {
        return ticketConverter.toDtoList(ticketRepository.getAllTickets());
    }

    public void createTicket(TicketDto ticketDto, Principal principal) {

    }

    public void editTicket(TicketDto ticketDto) {
    }
}

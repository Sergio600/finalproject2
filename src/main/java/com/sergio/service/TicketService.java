package com.sergio.service;

import com.sergio.converter.TicketConverter;
import com.sergio.domain.Ticket;
import com.sergio.domain.User;
import com.sergio.dto.TicketDto;
import com.sergio.enums.Role;
import com.sergio.enums.State;
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
    UserService userService;

    @Autowired
    TicketConverter ticketConverter;

    public List<Ticket> getAllTickets(Principal principal) {
        return ticketRepository.getAllTickets();
    }

    public Ticket getTicketById(int id) {
        return ticketRepository.getById(id).get();
    }

    public void createTicket(Ticket ticket, Principal principal, State state) {
        User user = userService.getCurrentUser(principal.getName());
        ticket.setUserOwner(user);
        ticket.setState(state);
        ticketRepository.save(ticket);
    }

    public void editTicket(int id, Ticket ticket, Principal principal, State state) {
        User user = userService.getCurrentUser(principal.getName());
        ticket.setId(id);
        ticket.setUserOwner(user);
        ticketRepository.updateTicket(ticket);
    }

    public List<Ticket> getUserTicketsByRole(Principal principal) {
        User user = userService.getCurrentUser(principal.getName());

        if (user.getRole() == Role.MANAGER) {
            return ticketRepository.getTicketsByUserRoleManager(user);
        }

        if (user.getRole() == Role.ENGINEER) {
            return ticketRepository.getTicketsByUserRoleEngineer(user);
        }

        if (user.getRole() == Role.EMPLOYEE) {
            return ticketRepository.getTicketsByUserRoleEmployee(user);
        }
        return null;
    }


    public List<Ticket> getUserTicketsFilter(Principal principal) {
        User user = userService.getCurrentUser(principal.getName());
        if (user.getRole() == Role.EMPLOYEE) {
            return ticketRepository.getTicketsByUserRoleEmployee(user);
        }

        if (user.getRole() == Role.MANAGER) {
            return ticketRepository.getTicketsByUserRoleManagerFilter(user);
        }

        if (user.getRole() == Role.ENGINEER) {
            return ticketRepository.getTicketsByUserRoleEngineerFilter(user);
        }
        return null;
    }




}

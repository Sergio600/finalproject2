package com.sergio.service;

import com.sergio.converter.TicketConverter;
import com.sergio.domain.Ticket;
import com.sergio.domain.User;
import com.sergio.dto.TicketDto;
import com.sergio.enums.Role;
import com.sergio.enums.State;
import com.sergio.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
@Lazy
public class TicketService {

    private  TicketRepository ticketRepository;
    private  UserService userService;
    private  TicketConverter ticketConverter;
    private  CategoryService categoryService;
    private  CommentService commentService;
    private  HistoryService historyService;

    @Autowired
    public TicketService(CategoryService categoryService,
                         @Lazy TicketConverter ticketConverter,
                         UserService userService,
                         TicketRepository ticketRepository,
                         CommentService commentService,
                         HistoryService historyService) {
        this.categoryService = categoryService;
        this.ticketConverter = ticketConverter;
        this.userService = userService;
        this.ticketRepository = ticketRepository;
        this.commentService = commentService;
        this.historyService = historyService;
    }

    public TicketService(){}

    public List<Ticket> getAllTickets() {
        return ticketRepository.getAllTickets();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.getById(id).get();
    }


    @Transactional
    public void createTicket(TicketDto ticketDto, Principal principal, State state) {
        User user = userService.getCurrentUser(principal.getName());
        Ticket ticket = ticketConverter.fromDto(ticketDto);
        ticket.setUserOwner(user);
        ticket.setState(state);
        ticket.setCategory(categoryService.getCategoryById(ticketDto.getCategory().getId()));
        ticketRepository.save(ticket);
        historyService.saveTicketCreationHistory(user, ticket);

        if(ticketDto.getComment() != null){
            commentService.createCommentToTicket(ticketDto.getComment(), user, ticket);
        }





    }

    public void editTicket(Long id, Ticket ticket, Principal principal, State state) {
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

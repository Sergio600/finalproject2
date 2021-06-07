package com.sergio.service;

import com.sergio.converter.TicketConverter;
import com.sergio.domain.Ticket;
import com.sergio.domain.User;
import com.sergio.dto.TicketDto;
import com.sergio.enums.Role;
import com.sergio.enums.State;
import com.sergio.repository.TicketRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;


@Service
public class TicketService {

    final static Logger LOGGER = Logger.getLogger(TicketService.class);

    private TicketRepository ticketRepository;
    private UserService userService;
    private TicketConverter ticketConverter;
    private CategoryService categoryService;
    private CommentService commentService;
    private HistoryService historyService;
    private JavaMailSender javaMailSender;
    private AttachmentService attachmentService;

    @Autowired
    public TicketService(CategoryService categoryService,
                         @Lazy TicketConverter ticketConverter,
                         UserService userService,
                         TicketRepository ticketRepository,
                         CommentService commentService,
                         HistoryService historyService,
                         AttachmentService attachmentService,
                         JavaMailSender javaMailSender) {
        this.categoryService = categoryService;
        this.ticketConverter = ticketConverter;
        this.userService = userService;
        this.ticketRepository = ticketRepository;
        this.commentService = commentService;
        this.historyService = historyService;
        this.attachmentService = attachmentService;
        this.javaMailSender = javaMailSender;

    }

    public TicketService() {
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.getAllTickets();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.getById(id).get();
    }


    @Transactional
    public void createTicket(TicketDto ticketDto, Principal principal, State state, CommonsMultipartFile[] files) {
        User user = userService.getCurrentUser(principal.getName());
        Ticket ticket = ticketConverter.fromDto(ticketDto);
        ticket.setUserOwner(user);
        ticket.setState(state);
        ticket.setCategory(categoryService.getCategoryById(ticketDto.getCategory().getId()));

        ticketRepository.save(ticket);
        historyService.saveTicketCreationHistory(user, ticket);

        if (ticketDto.getComment() != null) {
            commentService.createCommentToTicket(ticketDto.getComment(), user, ticket);
        }

        if (files != null && files.length > 0) {
            attachmentService.save(files, ticket);
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sergiosamusev600@gmail.com");
        message.setTo(userService.getManagersEmails());
        message.setSubject("New ticket for approval");
        message.setText(String.format("Dear managers, %nTicket with ID-%d is waiting for your approval", ticket.getId()));
        javaMailSender.send(message);
    }

    public void editTicket(Long id, TicketDto ticketDto, Principal principal) {
        Ticket ticket = ticketRepository.getById(id).get();
        ticket.setCategory(categoryService.getCategoryById(ticketDto.getCategory().getId()));
        ticket.setName(ticketDto.getName());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setUrgency(ticketDto.getUrgency());
        ticket.setDesiredResolutionDate(ticketDto.getDesiredResolutionDate());
        ticket.setState(ticketDto.getState());

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

package com.sergio.service;

import com.sergio.domain.History;
import com.sergio.domain.Ticket;
import com.sergio.domain.User;
import com.sergio.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Service
@Lazy
public class HistoryService {

    private HistoryRepository historyRepository;
    private UserService userService;
    private TicketService ticketService;

    @Autowired
    public HistoryService(HistoryRepository historyRepository,
                          UserService userService,
                          @Lazy TicketService ticketService) {
        this.historyRepository = historyRepository;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    public HistoryService() {
    }

    public List<History> getAllHistories() {
        return historyRepository.getAllHistories();
    }

    public List<History> getTicketHistory(Long id, Principal principal) {
        return historyRepository.getHistoryListByTicketId(id);
    }

    public void addHistoryToTicket(Long id, History history, Principal principal) {
        User user = userService.getCurrentUser(principal.getName());
        history.setTicket(ticketService.getTicketById(id));
        history.setUser(user);

        historyRepository.saveHistoryToTicket(history);
    }

    public void saveTicketCreationHistory(User user, Ticket ticket) {
        History history = new History();
        history.setTicket(ticket);
        history.setUser(user);
        history.setAction("CREATED");
        history.setDescription("Ticket is created.");
        history.setDate(new Timestamp(System.currentTimeMillis()));
        historyRepository.saveHistoryToTicket(history);
    }


}

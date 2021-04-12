package com.sergio.service;

import com.sergio.domain.History;
import com.sergio.domain.User;
import com.sergio.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    public List<History> getAllHistories(Principal principal) {
        return historyRepository.getAllHistories();
    }

    public List<History> getTicketHistory(int id, Principal principal) {
        return historyRepository.getHistoryListByTicketId(id);
    }

    public void addHistroryToTicket(int id, History history, Principal principal) {
        User user = userService.getCurrentUser(principal.getName());
        history.setTicket(ticketService.getTicketById(id));
        history.setUser(user);
        historyRepository.saveHistoryToTicket(history);
    }
}

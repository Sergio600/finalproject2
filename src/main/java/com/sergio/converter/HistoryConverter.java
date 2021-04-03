package com.sergio.converter;

import com.sergio.domain.History;
import com.sergio.dto.HistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class HistoryConverter {

    @Autowired
    TicketConverter ticketConverter;

    @Autowired
    UserConverter userConverter;

    public History fromDto(HistoryDto dto){
        History history = new History();

        history.setId(dto.getId());
        history.setDate(dto.getDate());
        history.setAction(dto.getAction());
        history.setDescription(dto.getDescription());
        history.setUser(userConverter.fromDto(dto.getUser()));
        history.setTicket(ticketConverter.fromDto(dto.getTicket()));

        return history;

    }
}

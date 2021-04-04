package com.sergio.converter;

import com.sergio.domain.History;
import com.sergio.dto.HistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Lazy
public class HistoryConverter {

    @Autowired
    TicketConverter ticketConverter;

    public History fromDto(HistoryDto dto){
        History history = new History();

        history.setId(dto.getId());
        history.setDate(dto.getDate());
        history.setAction(dto.getAction());
        history.setDescription(dto.getDescription());
        history.setTicket(ticketConverter.fromDto(dto.getTicket()));

        return history;

    }

    public HistoryDto toDto(History history){
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(history.getId());
        historyDto.setDate(history.getDate());
        historyDto.setAction(history.getAction());
        historyDto.setDescription(history.getDescription());
        historyDto.setTicket(ticketConverter.toDto(history.getTicket()));

        return historyDto;
    }

    public List<History> fromDtoList(List<HistoryDto> dtoList){
        return dtoList
                .stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

    public List<HistoryDto> toDtoList(List<History> list){
        return list
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

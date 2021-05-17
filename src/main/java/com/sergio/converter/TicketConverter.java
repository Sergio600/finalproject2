package com.sergio.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergio.domain.Ticket;
import com.sergio.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Lazy
public class TicketConverter {

    @Autowired
    UserConverter userConverter;

    @Autowired
    CategoryConverter categoryConverter;

    @Autowired
    HistoryConverter historyConverter;

    @Autowired
    CommentConverter commentConverter;

    @Autowired
    FeedbackConverter feedbackConverter;

    private ObjectMapper objectMapper;

    @Autowired
    public TicketConverter(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public Ticket fromDto(TicketDto dto) {
        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setName(dto.getName());
        ticket.setDescription(dto.getDescription());
        ticket.setCreatedOn(dto.getCreatedOn());
        ticket.setDesiredResolutionDate(dto.getDesiredResolutionDate());
        ticket.setState(dto.getState());
        ticket.setUrgency(dto.getUrgency());
//        ticket.setAttachment(dto.getAttachment());

//        ticket.setUserAssignee(userConverter.fromDto(dto.getUserAssignee()));
//        ticket.setUserOwner(userConverter.fromDto(dto.getUserOwner()));
//        ticket.setUserApprover(userConverter.fromDto(dto.getUserApprover()));
        ticket.setCategory(categoryConverter.fromDto(dto.getCategory()));

//        ticket.setHistoryList(historyConverter.fromDtoList(dto.getHistoryList()));
//        ticket.setCommentList(commentConverter.fromDtoList(dto.getCommentList()));
//        ticket.setFeedbackList(feedbackConverter.fromDtoList(dto.getFeedbackList()));

        return ticket;
    }

    public TicketDto toDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();

        ticketDto.setId(ticket.getId());
        ticketDto.setName(ticket.getName());
        ticketDto.setDescription(ticket.getDescription());
        ticketDto.setCreatedOn(ticket.getCreatedOn());
        ticketDto.setDesiredResolutionDate(ticket.getDesiredResolutionDate());
        ticketDto.setState(ticket.getState());
        ticketDto.setUrgency(ticket.getUrgency());
//        ticketDto.setAttachment(ticket.getAttachment());

//        ticketDto.setUserAssignee(userConverter.toDto(ticket.getUserAssignee()));
//        ticketDto.setUserOwner(userConverter.toDto(ticket.getUserOwner()));
//        ticketDto.setUserApprover(userConverter.toDto(ticket.getUserApprover()));
        ticketDto.setCategory(categoryConverter.toDto(ticket.getCategory()));

//        ticketDto.setHistoryList(historyConverter.toDtoList(ticket.getHistoryList()));
//        ticketDto.setCommentList(commentConverter.toDtoList(ticket.getCommentList()));
//        ticketDto.setFeedbackList(feedbackConverter.toDtoList(ticket.getFeedbackList()));

        return ticketDto;
    }

    public List<Ticket> fromDtoList(List<TicketDto> dtoList) {
        return dtoList
                .stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

    public List<TicketDto> toDtoList(List<Ticket> list) {
        return list
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TicketDto fromJson(String ticketJson) {
        TicketDto ticketDto = new TicketDto();
        if (!ticketJson.isEmpty()) {

            try {
                ticketDto = objectMapper.readValue(ticketJson, TicketDto.class);
                System.out.println(ticketDto);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return ticketDto;
    }
}

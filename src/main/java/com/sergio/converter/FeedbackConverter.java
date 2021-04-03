package com.sergio.converter;

import com.sergio.domain.Feedback;
import com.sergio.dto.FeedbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Lazy
public class FeedbackConverter {

    @Autowired
    UserConverter userConverter;

    @Autowired
    TicketConverter ticketConverter;

    public Feedback fromDto (FeedbackDto dto){
        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setRate(dto.getRate());
        feedback.setDate(dto.getDate());
        feedback.setText(dto.getText());

        feedback.setUser(userConverter.fromDto(dto.getUser()));
        feedback.setTicket(ticketConverter.fromDto(dto.getTicket()));

        return feedback;
    }


    public FeedbackDto toDto(Feedback feedback){
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(feedback.getId());
        feedbackDto.setRate(feedback.getRate());
        feedbackDto.setDate(feedback.getDate());
        feedbackDto.setText(feedback.getText());

        feedbackDto.setUser(userConverter.toDto(feedback.getUser()));
        feedbackDto.getTicket(ticketConverter.toDto(feedback.getTicket()));

        return feedbackDto;
    }

    public List<Feedback> fromDtoList(List<FeedbackDto> dtoList){
        return dtoList
                .stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

    public List<FeedbackDto> toDtoList(List<Feedback> list){
        return list
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }



}

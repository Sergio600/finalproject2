package com.sergio.converter;

import com.sergio.domain.Attachment;
import com.sergio.dto.AttachmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class AttachmentConverter {
    @Autowired
    TicketConverter ticketConverter;

    public Attachment fromDto(AttachmentDto dto){
        Attachment attachment = new Attachment();
        attachment.setId(dto.getId());
        attachment.setBlob(dto.getBlob());
        attachment.setName(dto.getName());
        attachment.setTicket(ticketConverter.fromDto(dto.getTicket()));

        return attachment;
    }

    public AttachmentDto toDto(Attachment attachment){
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setId(attachment.getId());
        attachmentDto.setBlob(attachment.getBlob());
        attachmentDto.setName(attachment.getName());
        attachmentDto.setTicket(ticketConverter.toDto(attachment.getTicket()));

        return attachmentDto;
    }

}

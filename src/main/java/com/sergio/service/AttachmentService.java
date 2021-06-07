package com.sergio.service;

import com.sergio.domain.Attachment;
import com.sergio.domain.Ticket;
import com.sergio.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class AttachmentService {

    private AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository){
        this.attachmentRepository= attachmentRepository;
    }

    public void save(CommonsMultipartFile[] files, Ticket ticket) {
        Attachment attachment = new Attachment();
        attachment.setTicket(ticket);
        byte[] bytes = files[0].getBytes();
        try {
            Blob blob = new SerialBlob(bytes);
            attachment.setBlob(blob);
        } catch (SQLException e) {
            System.out.println("something goes wrong");
        }
        attachmentRepository.save(attachment);
    }
}

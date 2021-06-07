package com.sergio.repository;

import com.sergio.domain.Attachment;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AttachmentRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Attachment getAttachmentByTicketId(Long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Attachment a where a.ticket_id = :id", Attachment.class);
        query.setParameter("id", id);
        Attachment attachment = (Attachment) query.getSingleResult();
        return attachment;
    }

    public void save(Attachment attachment){
        System.out.println(attachment.getId());
        sessionFactory.getCurrentSession().save(attachment);
    }
}

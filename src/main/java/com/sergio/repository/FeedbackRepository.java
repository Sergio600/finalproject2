package com.sergio.repository;

import com.sergio.domain.Feedback;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FeedbackRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Feedback save(Feedback feedback){
        sessionFactory.getCurrentSession().save(feedback);
        return feedback;
    }

    public List<Feedback> getTicketFeedback(Long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Feedback where ticket_id = :id");
        query.setParameter("id", id);
        return query.list();
    }
}

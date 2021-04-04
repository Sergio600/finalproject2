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

    public List<Feedback> getFeedbackListByTicketId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Feedback f where f.ticket_id = :id", Feedback.class);
        return query.list();
    }
}

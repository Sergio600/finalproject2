package com.sergio.repository;

import com.sergio.domain.History;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HistoryRepository {

    @Autowired
    SessionFactory sessionFactory;

    public List<History> getHistoryListByTicketId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from History h where h.ticket_id = :id", History.class);
        return query.list();
    }

}

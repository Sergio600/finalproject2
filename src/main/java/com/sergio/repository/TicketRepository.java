package com.sergio.repository;

import com.sergio.domain.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TicketRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Ticket save(Ticket ticket) {
        sessionFactory.getCurrentSession().save(ticket);
        return ticket;
    }

    public void updateTicket(Ticket ticket) {
        sessionFactory.getCurrentSession().update(ticket);
    }

    public Optional<Ticket> getById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Ticket t where t.id = :id");
        query.setParameter("id", id);

        Ticket ticket = (Ticket) query.getSingleResult();
        if (ticket == null) {
            return Optional.empty();
        } else {
            return Optional.of(ticket);
        }
    }

    public List<Ticket> getAllTickets() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Ticket", Ticket.class);
        return query.list();
    }

    public List<Ticket> getTicketsByUser(int ownerId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Ticket where owner_id = :id", Ticket.class);
        query.setParameter("id", ownerId);
        return query.list();
    }



}

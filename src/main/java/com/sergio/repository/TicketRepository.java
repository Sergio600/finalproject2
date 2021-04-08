package com.sergio.repository;

import com.sergio.domain.Ticket;
import com.sergio.domain.User;
import com.sergio.enums.State;
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

    public List<Ticket> getTicketsByUserRoleEmployee(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Ticket where owner_id = :id", Ticket.class);
        query.setParameter("id", user.getId());
        return query.list();
    }

    public List<Ticket> getTicketsByUserRoleManager(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Ticket where owner_id = :id " +
                "or state_id = :stateIdNew " +
                "or (approver_id = :approverID " +
                "and (state_id = :stateIdApproved " +
                "or state_id = :stateIdDeclined " +
                "or state_id = :stateIdCancelled " +
                "or state_id = :stateIdInProgress " +
                "or state_id = :stateIdDone))", Ticket.class);
        query.setParameter("id", user.getId());
        query.setParameter("stateIdNew", State.NEW.ordinal());
        query.setParameter("approverID", user.getId());
        query.setParameter("stateIdApproved", State.APPROVED.ordinal());
        query.setParameter("stateIdDeclined", State.DECLINED.ordinal());
        query.setParameter("stateIdCancelled", State.CANCELED.ordinal());
        query.setParameter("stateIdInProgress", State.INPROGRESS.ordinal());
        query.setParameter("stateIdDone", State.DONE.ordinal());
        return query.list();
    }

    public List<Ticket> getTicketsByUserRoleEngineer(User user) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Ticket where (assignee_id = :id " +
                        "and (state_id = :stateIdInProgress " +
                        "or state_id = :stateIdDone)) " +
                        "or state_id = :stateIdApproved", Ticket.class);

        query.setParameter("id", user.getId());
        query.setParameter("stateIdInProgress", State.INPROGRESS.ordinal());
        query.setParameter("stateIdDone", State.DONE.ordinal());
        query.setParameter("stateIdApproved", State.APPROVED.ordinal());
        return query.list();
    }

    public List<Ticket> getTicketsByUserRoleManagerFilter(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Ticket where owner_id = :id " +
                "or (approver_id = :approverID " +
                "and state_id = :stateIdApproved)", Ticket.class);
        query.setParameter("id", user.getId());
        query.setParameter("approverID", user.getId());
        query.setParameter("stateIdApproved", State.APPROVED.ordinal());
        return query.list();
    }

    public List<Ticket> getTicketsByUserRoleEngineerFilter(User user) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Ticket where assignee_id = :id", Ticket.class);
        query.setParameter("id", user.getId());
        return query.list();
    }




}

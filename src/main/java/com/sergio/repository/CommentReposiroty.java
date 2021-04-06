package com.sergio.repository;

import com.sergio.domain.Comment;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommentReposiroty {

    @Autowired
    SessionFactory sessionFactory;

    public Comment addCommentToTicket(Comment comment){
        sessionFactory.getCurrentSession().save(comment);
        return comment;
    }

    public List<Comment> getTicketComments(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Comment where ticket_id = :id");
        query.setParameter("id", id);
        return query.list();
    }

    public List<Comment> getAllComments() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Comment", Comment.class);
        return query.list();
    }
}

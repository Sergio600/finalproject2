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

    public List<Comment> getCommentByTicketId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Comment com where com.ticket_id = :id", Comment.class);
        query.setParameter("id", id);
        return query.list();
    }
}

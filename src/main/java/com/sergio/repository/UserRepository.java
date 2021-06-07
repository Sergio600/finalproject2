package com.sergio.repository;

import com.sergio.domain.User;
import com.sergio.enums.Role;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @Autowired
    SessionFactory sessionFactory;

    public User save(User user){
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    public User getById(Long id){
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        return user;
    }

    public User getByEmail(String email){
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from User u where u.email = :email", User.class);
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        return user;
    }

    public List<User> getManagers(){
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("from User u where u.role = :role", User.class);
        query.setParameter("role", Role.MANAGER);

        return query.list();
    }
}

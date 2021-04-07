package com.sergio.repository;

import com.sergio.domain.Category;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Category getCategoryById(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Category c where c.id = :id", Category.class);
        query.setParameter("id", id);
        Category category = (Category) query.getSingleResult();
        return category;
    }

    public List<Category> getCategories(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Category", Category.class);
        return query.list();
    }
}

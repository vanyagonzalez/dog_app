package com.piv.repositories;

import com.piv.model.Dog;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ivan_Pavlyukovets on 6/15/2016.
 */
public class DogRepositoryHibernate implements DogRepositoryInterface {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Dog> getAllDogs() {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Dog");
        List<Dog> dogs = query.getResultList();
        return dogs;
    }

    @Override
    @Transactional
    public Dog createDog(Dog dog) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(dog);

        return dog;
    }
}

package org.travelagency.repository;

import org.travelagency.model.Destination;
import org.travelagency.model.Package;
import org.travelagency.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserRepository
{
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.travelagency");

    public User addUser(User user)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public User updateUser(User user)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public List<User> findByUsername(String username)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select u from User u where u.username = :username");
        q.setParameter("username", username);
        return q.getResultList();
    }

    public List<User> findByUsernameAndPassword(User user)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select u from User u where u.username = :username and u.password = :password");
        q.setParameter("username", user.getUsername());
        q.setParameter("password", user.getPassword());
        return q.getResultList();
    }

    public List<Package> getVacations(User user)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select u.vacations from User u where u.Id = :id");
        q.setParameter("id", user.getId());
        return q.getResultList();
    }
}

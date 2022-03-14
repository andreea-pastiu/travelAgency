package org.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.travelagency.model.Destination;
import org.travelagency.model.Package;
import org.travelagency.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class DestinationRepository
{
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.travelagency");

    public Destination addDestination(Destination destination)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
        return destination;
    }

    public Destination deleteDestination(Destination destination)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Destination destinationToDelete = em.find(Destination.class, destination.getId());
        em.remove(destinationToDelete);
        em.getTransaction().commit();
        em.close();
        return destination;
    }

    public List<Destination> getAllDestinations()
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select d from Destination d");
        List<Destination> destinations = new ArrayList<Destination>();
        destinations = q.getResultList();
        return destinations;
    }

    public List<Destination> getByName(String name)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select d from Destination d where d.name = :name");
        q.setParameter("name", name);
        return q.getResultList();
    }
}

package org.travelagency.repository;

import org.travelagency.model.Destination;
import org.travelagency.model.Package;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackageRepository
{
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.travelagency");

    public Package addPackage(Package packageObj)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(packageObj);
        em.getTransaction().commit();
        em.close();
        return packageObj;
    }

    public Package editPackage(Package packageObj)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(packageObj);
        em.getTransaction().commit();
        em.close();
        return packageObj;
    }

    public Package deletePackage(Package packageObj)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Package packageToDelete = em.find(Package.class, packageObj.getId());
        em.remove(packageToDelete);
        em.getTransaction().commit();
        em.close();
        return packageObj;
    }

    public List<Package> getAllPackages()
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select p from Package p");
        List<Package> packages = new ArrayList<Package>();
        packages = q.getResultList();
        return packages;
    }

    public List<Package> getAllValidPackages()
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select p from Package p where p.currentPeople < p.maxPeople");
        List<Package> packages = new ArrayList<Package>();
        packages = q.getResultList();
        return packages;
    }

    public List<Package> filterPackages(Date startDate, Date endDate, BigDecimal price, String destination)
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createQuery("select p from Package p where p.currentPeople < p.maxPeople and p.startDate >= :startDate and p.endDate <= :endDate and p.price < :price and p.destination.name = :destination");
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);
        q.setParameter("price", price);
        q.setParameter("destination", destination);
        List<Package> packages = new ArrayList<Package>();
        packages = q.getResultList();
        return packages;
    }
}

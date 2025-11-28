package com.example.dit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.example.dit.model.Emission;

/*
 * Dao for Emission entity.
 * Handles all my database operations for emissions.
 */
public class EmissionDAO {
    
    // factory for creating EntityManager objects
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("GreenhousePU");
    
    // method to get a new EntityManager
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // create / insert a new emission row
    public void create(Emission e) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(e);          // ask JPA to save the entity
        em.getTransaction().commit();
        em.close();
    }
    
    // find one emission by its primary key
    public Emission find(int id) {
        EntityManager em = getEntityManager();
        Emission e = em.find(Emission.class, id);
        em.close();
        return e;
    }
    
    // get all emissions in the table
    @SuppressWarnings("unchecked")
    public List<Emission> findAll() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT e FROM Emission e");
        List<Emission> list = q.getResultList();
        em.close();
        return list;
    }
    
    // get emissions by category (for "view by category")
    @SuppressWarnings("unchecked")
    public List<Emission> findByCategory(String category) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT e FROM Emission e WHERE e.category = :cat");
        q.setParameter("cat", category);
        List<Emission> list = q.getResultList();
        em.close();
        return list;
    }
    
    // update an existing emission
    public void update(Emission e) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(e);            // merge attaches the detached entity and updates it
        em.getTransaction().commit();
        em.close();
    }
    
    // delete an emission
    public void delete(Emission e) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Emission attached = em.merge(e); // make sure it is managed
        em.remove(attached);             // then remove it
        em.getTransaction().commit();
        em.close();
    }
}


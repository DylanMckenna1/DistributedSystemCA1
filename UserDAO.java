package com.example.dit.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.dit.model.User;

/*
 * DAO for User entity.
 */
public class UserDAO {


    protected static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("GreenhousePU");

    public UserDAO() {
      
    }

    // save a new user
    public void persist(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    // remove a user
    public void remove(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(user));   // attach first, then remove
        em.getTransaction().commit();
        em.close();
    }

    // update user details
    public User merge(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User updatedUser = em.merge(user);
        em.getTransaction().commit();
        em.close();
        return updatedUser;
    }

    // get all users 
    public List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<User> usersFromDB = new ArrayList<User>();
        usersFromDB = em.createNamedQuery("User.findAll", User.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return usersFromDB;
    }

    // find one user by username / login
    @SuppressWarnings("unchecked")
    public User getUserByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        List<User> users = (List<User>)
                em.createNamedQuery("User.findByUsername")
                  .setParameter("username", username)
                  .getResultList();
        em.close();

        // if there are users with that username, just return the first one
        User u = null;
        for (User user : users) {
            u = user;
        }
        return u;
    }
}


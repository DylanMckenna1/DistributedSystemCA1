package com.myfitness.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.myfitness.model.Payment;

public class PaymentDao {

    protected static EntityManagerFactory emf =
    Persistence.createEntityManagerFactory("fitnessPU");

    public PaymentDao() {
        // Default constructor
    }
// create
    public void persist(Payment payment) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(payment);
        em.getTransaction().commit();
        em.close();
    }
// delete
    public void removePayment(Payment payment) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(payment));
        em.getTransaction().commit();
        em.close();
    }
// update 
    public Payment merge(Payment payment) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Payment updatedPayment = em.merge(payment);
        em.getTransaction().commit();
        em.close();
        return updatedPayment;
    }
// read all payments
    public List<Payment> getAllPayments() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Payment> payments = new ArrayList<Payment>();
        payments = em.createQuery("from Payment").getResultList();
        em.getTransaction().commit();
        em.close();
        return payments;
    }
// read by payment id 
    public Payment getPaymentById(String paymentId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Payment payment = em.createQuery("SELECT p FROM Payment p WHERE p.paymentId = :paymentId", Payment.class)
                .setParameter("paymentId", paymentId)
                .getSingleResult();
        em.getTransaction().commit();
        em.close();
        return payment;
    }
}

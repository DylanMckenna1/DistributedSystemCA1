package com.myfitness.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.myfitness.model.MembershipPlan;

public class MembershipPlanDao {
	
    protected static EntityManagerFactory emf =
    Persistence.createEntityManagerFactory("fitnessPU");

    public MembershipPlanDao() {
        // Default constructor
    }
// create 
    public void persist(MembershipPlan membershipPlan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(membershipPlan); // inserting plan into the db
        em.getTransaction().commit();
        em.close();
    }
// delete 
    public void removeMembershipPlan(MembershipPlan membershipPlan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(membershipPlan));
        em.getTransaction().commit();
        em.close();
    }
// update
    public MembershipPlan merge(MembershipPlan membershipPlan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MembershipPlan updatedPlan = em.merge(membershipPlan);
        em.getTransaction().commit();
        em.close();
        return updatedPlan;
    }
// reads all plans
    public List<MembershipPlan> getAllMembershipPlans() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<MembershipPlan> plans = new ArrayList<MembershipPlan>();
        plans = em.createQuery("from MembershipPlan").getResultList(); // jpql slecting from membershipplan
        em.getTransaction().commit();
        em.close();
        return plans;
    }
// reads by name
    public MembershipPlan getPlanByName(String planName) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MembershipPlan plan = em.createQuery("SELECT p FROM MembershipPlan p WHERE p.planName = :planName", MembershipPlan.class)
                .setParameter("planName", planName)
                .getSingleResult();
        em.getTransaction().commit();
        em.close();
        return plan;
    }
}


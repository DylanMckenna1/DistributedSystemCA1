package com.myfitness.dao;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager; //Jpa imports for entityMan/ persistence 
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.myfitness.model.Member; // imprt the member entity

public class MemberDao {
// creates 1 shared connection for hibernate nd JPA to manage connections 
    protected static EntityManagerFactory emf = 
    Persistence.createEntityManagerFactory("fitnessPU"); // persistence xml name ''fitnesspu''

    public MemberDao() {}

    // create nd insert 
    public void persist(Member member) {
        EntityManager em = emf.createEntityManager();  // start a new connection
        em.getTransaction().begin(); // transaction beginning 
        em.persist(member); // add new member to datbase
        em.getTransaction().commit(); // save changes 'commit
        em.close(); // connection close
    }
 // delete member 
    public void removeMember(Member member) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(member)); // merge ensuring obj is attached to persistence before deletion
        em.getTransaction().commit();
        em.close();
    }
// update 
    public Member merge(Member member) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Member updatedMember = em.merge(member); // update existing row in database
        em.getTransaction().commit();
        em.close();
        return updatedMember; // returns my updated member
    }
// get alll my members
    public List<Member> getAllMembers() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Member> members = new ArrayList<Member>(); // use jpql to select all members
        members = em.createQuery("from Member").getResultList();
        em.getTransaction().commit();
        em.close();
        return members;
    }
// get member by name 
    public Member getMemberByName(String name) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Member m = em.createQuery("SELECT p FROM Member p WHERE p.name = :name", Member.class) // query to get member where name matches
                .setParameter("name", name)
                .getSingleResult();
        em.getTransaction().commit();
        em.close();
        return m;
    }
}


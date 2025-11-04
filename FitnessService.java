package com.myfitness.service;
// import my library 
import java.util.ArrayList;

import java.util.List;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
// import dao and java classes
import com.myfitness.dao.MemberDao;
import com.myfitness.dao.MembershipPlanDao;
import com.myfitness.dao.PaymentDao;
import com.myfitness.model.Member;
import com.myfitness.model.MembershipPlan;
import com.myfitness.model.Payment;
// setting my url bse  path for all my rest endpoints in this class
@Path("/fitnessserviceDBCRUD") 
public class FitnessService {
// creates list of members in memory


    // Member crud 
   
    @GET
    @Path("/membersjsonfromdb") // returns all members in Json
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> getMembersFromDBJSON() {
        MemberDao dao = new MemberDao();
        return dao.getAllMembers();
    }

    @GET
    @Path("/jsonDB/member/{name}") // find individual member by name
    @Produces(MediaType.APPLICATION_JSON)
    public Member getMemberByNameJSON(@PathParam("name") String name) {
        MemberDao dao = new MemberDao();
        return dao.getMemberByName(name);
    }

    @POST
    @Path("/newMember")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addMemberToDB(Member member) {
        MemberDao dao = new MemberDao();
        dao.persist(member); // saves user to database
        return "Member added to DB: " + member.getName();
    }

    @PUT
    @Path("/updateMember")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Member updateMember(Member member) {
        MemberDao dao = new MemberDao();
        return dao.merge(member); // updates record in db
    }

    @DELETE
    @Path("/deleteMember/{name}")
    @Produces("text/plain")
    public String deleteMember(@PathParam("name") String name) {
        MemberDao dao = new MemberDao();
        Member member = dao.getMemberByName(name); // find the user by name 'member'
        dao.removeMember(member); // then it removes them 
        return "Member " + name + " deleted";
    }

    // Membership plan

    @GET
    @Path("/membershipplans")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MembershipPlan> getAllPlans() {
        MembershipPlanDao dao = new MembershipPlanDao();
        return dao.getAllMembershipPlans();
    }

    @POST
    @Path("/addplan")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addPlan(MembershipPlan plan) {
        MembershipPlanDao dao = new MembershipPlanDao();
        dao.persist(plan);
        return "Membership plan added: " + plan.getPlanName();
    }

    @DELETE
    @Path("/deleteplan/{planName}")
    @Produces("text/plain")
    public String deletePlan(@PathParam("planName") String planName) {
        MembershipPlanDao dao = new MembershipPlanDao();
        MembershipPlan plan = dao.getPlanByName(planName);
        dao.removeMembershipPlan(plan);
        return "Plan " + planName + " deleted";
    }

    // Payments

    @GET
    @Path("/payments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Payment> getAllPayments() {
        PaymentDao dao = new PaymentDao();
        return dao.getAllPayments();
    }

    @POST
    @Path("/addpayment")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addPayment(Payment payment) {
        PaymentDao dao = new PaymentDao();
        dao.persist(payment);
        return "Payment added for member ID: " + payment.getMemberId();
    }

    @DELETE
    @Path("/deletepayment/{paymentId}")
    @Produces("text/plain")
    public String deletePayment(@PathParam("paymentId") String paymentId) {
        PaymentDao dao = new PaymentDao();
        Payment payment = dao.getPaymentById(paymentId);
        dao.removePayment(payment);
        return "Payment ID " + paymentId + " deleted";
    }
}

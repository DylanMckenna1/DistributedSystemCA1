package com.example.dit;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.example.dit.dao.EmissionDAO;
import com.example.dit.dao.UserDAO;
import com.example.dit.model.Emission;
import com.example.dit.model.User;

/*
 * REST service for greenhouse gas emissions.
 */
@Path("/emissions")
public class EmissionService {
    
    private EmissionDAO emissionDAO = new EmissionDAO();
    private UserDAO userDAO = new UserDAO();

  
    // User endpoints 

    // registering a new user
    @POST
    @Path("/register")
    @Consumes("application/json")
    public void registerUser(User u) {
        // insert 
        userDAO.persist(u);
    }

    // login-return the user if 
    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public User login(User u) {
        User found = userDAO.getUserByUsername(u.getUsername());

        // if user exists & password matches, return user
        if (found != null && found.getPassword().equals(u.getPassword())) {
            return found;
        }
        return null; // return null if failed  
    }


    // Emission crud and views
   

    //all emissions
    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Emission> getAllEmissions() {
        return emissionDAO.findAll();
    }

    // list by category
    @GET
    @Path("/category/{cat}")
    @Produces("application/json")
    public List<Emission> getByCategory(@PathParam("cat") String category) {
        return emissionDAO.findByCategory(category);
    }

    // create new emission
    @POST
    @Path("/create")
    @Consumes("application/json")
    public void createEmission(Emission e) {
        emissionDAO.create(e);
    }

    // update emission
    @PUT
    @Path("/update")
    @Consumes("application/json")
    public void updateEmission(Emission e) {
        emissionDAO.update(e);
    }

    // delete emission
    @DELETE
    @Path("/delete/{id}")
    public void deleteEmission(@PathParam("id") int id) {
        Emission e = emissionDAO.find(id);
        if (e != null) {
            emissionDAO.delete(e);
        }
    }

    
    // Approval endpoint
 

    // approve an emission
    @PUT
    @Path("/approve/{id}/{username}")
    public void approveEmission(
            @PathParam("id") int id,
            @PathParam("username") String username) {

        Emission e = emissionDAO.find(id);
        if (e != null) {
            e.setApproved(true);
            e.setApprovedBy(username);
            emissionDAO.update(e);
        }
    }

  
    // Data import xml and json


    @GET
    @Path("/import/xml")
    public void importXML() {
   
    }

    @GET
    @Path("/import/json")
    public void importJSON() {
     
    }
}

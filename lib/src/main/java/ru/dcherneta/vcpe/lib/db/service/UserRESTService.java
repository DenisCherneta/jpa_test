package ru.dcherneta.vcpe.lib.db.service;

import ru.dcherneta.vcpe.lib.db.model.UserEntity;
import ru.dcherneta.vcpe.lib.db.service.interfaces.EntityRESTService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by DCherneta on 13.02.2017.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
//@Stateless
public class UserRESTService implements EntityRESTService<UserEntity>{

    @PersistenceContext(unitName = "vcpeTestDS")
    private EntityManager entityManager;

    @GET
    //@Path("/get/{id}")
    public UserEntity get(@QueryParam("id") Long id){
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    @POST
    //@Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity create(UserEntity user){
        System.out.println(">> create User ... <<");
        if (user!=null){
            System.out.println(">> User name: " + user.getName());
            //EntityTransaction entityTransaction = entityManager.getTransaction();
            //entityTransaction.begin();
            entityManager.persist(user);
            entityManager.flush();
            /*entityManager.refresh(user);*/
            //entityTransaction.commit();
        }
        return user;
    }

    @Override
    @DELETE
    //@Produces(MediaType.APPLICATION_JSON)
    //@Path("/del/{id}")
    public UserEntity delete(@QueryParam("id") Long id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        if (user != null){
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.remove(user);
            entityTransaction.commit();
        }
        return user;
    }

    @Override
    @PUT
    //@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@Path("/refresh")
    public UserEntity update(UserEntity user) {
        if (user!=null){
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            user = entityManager.merge(user);
            entityTransaction.commit();
        }
        return user;
    }

}

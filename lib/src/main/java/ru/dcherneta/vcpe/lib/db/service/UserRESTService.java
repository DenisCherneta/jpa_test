package ru.dcherneta.vcpe.lib.db.service;

import ru.dcherneta.vcpe.lib.db.model.ItemEntity;
import ru.dcherneta.vcpe.lib.db.model.UserEntity;
import ru.dcherneta.vcpe.lib.db.service.interfaces.EntityRESTService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by DCherneta on 13.02.2017.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
//@Stateless
public class UserRESTService implements EntityRESTService<UserEntity>{

    @PersistenceContext(unitName = "vcpeTestDS")
    private EntityManager entityManager;

    @Override
    @GET
    public UserEntity get(@QueryParam("id") Long id){
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity create(UserEntity user){
        //System.out.println(">> create User ... <<");
        if (user!=null){
            //System.out.println(">> User name: " + user.getName());
            for(ItemEntity item : user.getUserItems()){
                item.setUser(user);
            }
            entityManager.persist(user);
            entityManager.flush();
        }
        return user;
    }

    @Override
    @DELETE
    public UserEntity delete(@QueryParam("id") Long id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        if (user != null){
            System.out.println("Delete user ("+id+"): " + user.getName());
            entityManager.remove(user);
        }
        return user;
    }

    @Override
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity update(UserEntity user) {
        if (user!=null){
            System.out.println("Update: "+ user);
            for(ItemEntity item : user.getUserItems()){
                System.out.println(item.toString());
                item.setUser(user);
            }
            user = entityManager.merge(user);
        }
        return user;
    }

}

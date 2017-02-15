package ru.dcherneta.vcpe.lib.db.service;

import ru.dcherneta.vcpe.lib.db.model.ItemEntity;
import ru.dcherneta.vcpe.lib.db.service.interfaces.EntityRESTService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by DCherneta on 14.02.2017.
 */
@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class ItemRESTService implements EntityRESTService<ItemEntity>{

    @PersistenceContext(unitName = "vcpeTestDS")
    private EntityManager entityManager;

    @Override
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public ItemEntity get(@QueryParam("id") int id){
        return entityManager.find(ItemEntity.class, id);
    }
    /*
    public Response get(@QueryParam("id") int id){
        return Response.ok(entityManager.find(ItemEntity.class, id)).build();
    }*/

    @Override
    @POST
    //@Path("/add")
    //@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ItemEntity create(ItemEntity item){
        if (item !=null){
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(item);
            entityTransaction.commit();
            entityManager.refresh(item);
        }
        return item;
    }

    @Override
    @DELETE
    //@Produces(MediaType.APPLICATION_JSON)
    //@Path("/del/{id}")
    public ItemEntity delete(@QueryParam("id") Long id) {
        ItemEntity item = entityManager.find(ItemEntity.class, id);
        if (item != null){
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.remove(item);
            entityTransaction.commit();
        }
        return item;
    }

    @Override
    @PUT
    //@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@Path("/refresh")
    public ItemEntity update(ItemEntity item) {
        if (item!=null){
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            item = entityManager.merge(item);
            entityTransaction.commit();
        }
        return item;
    }

}

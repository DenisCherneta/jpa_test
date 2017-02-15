package ru.dcherneta.vcpe.lib.db.service;

import ru.dcherneta.vcpe.lib.db.model.ItemEntity;
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
 * Created by DCherneta on 14.02.2017.
 */
@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Transactional(value = Transactional.TxType.REQUIRES_NEW)
//@Stateless
public class ItemRESTService implements EntityRESTService<ItemEntity>{

    @PersistenceContext(unitName = "vcpeTestDS")
    private EntityManager entityManager;

    @Override
    @GET
    public ItemEntity get(@QueryParam("id") Long id){
        return entityManager.find(ItemEntity.class, id);
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ItemEntity create(ItemEntity item){
        if (item !=null){
            entityManager.persist(item);
        }
        return item;
    }

    @Override
    @DELETE
    public ItemEntity delete(@QueryParam("id") Long id) {
        ItemEntity item = entityManager.find(ItemEntity.class, id);
        if (item != null){
            entityManager.remove(item);
        }
        return item;
    }

    @Override
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public ItemEntity update(ItemEntity item) {
        if (item!=null){
            item = entityManager.merge(item);
        }
        return item;
    }

}

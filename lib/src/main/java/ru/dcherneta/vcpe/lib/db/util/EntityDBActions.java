package ru.dcherneta.vcpe.lib.db.util;

import ru.dcherneta.vcpe.lib.db.model.ItemEntity;
import ru.dcherneta.vcpe.lib.db.model.UserEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by DCherneta on 09.02.2017.
 */
public class EntityDBActions {
    /*глупо так было делать - все равно каждый раз инициализировать приходиться
    * */
    private EntityManager _entityManager = EntityDBManager.getInstance().getEntityManagerFactory().createEntityManager();

    public <T> T insertEntity (T entity){
        EntityTransaction entityTransaction = _entityManager.getTransaction();
        try {
            entityTransaction.begin();
            _entityManager.persist(entity);
            entityTransaction.commit();
            return entity;
        }catch(Exception ex){
            throw ex;
        }
    }

    public <T> T getEntityById(Class <T> entityClass, Object primaryKey){
        try {
            return _entityManager.find(entityClass, primaryKey);
        }catch(Exception ex){
            throw ex;
        }
    }

    public List<UserEntity> getUsersByName(String name){
        try {
            TypedQuery query = _entityManager.createNamedQuery("UserEntity.findByName", UserEntity.class).setParameter("name", name);
            return query.getResultList();
        }catch(Exception ex){
            throw ex;
        }
    }

    public UserEntity updateUserNameById(Object primaryKey, String newName){
        EntityTransaction entityTransaction = _entityManager.getTransaction();
        try {
            UserEntity user = _entityManager.find(UserEntity.class, primaryKey);
            if (user != null){
                System.out.println(">> Update: Old Name '/" + user.getName() + "'/ for User whith ID : " + user.getUserId());
                entityTransaction.begin();
                user.setName(newName);
                entityTransaction.commit();
                System.out.println(">> Update: New Name set to '/" + user.getName() + "'/ for User whith ID : " + user.getUserId());
            }
            return user;
        }catch(Exception ex){
            entityTransaction.rollback();
            throw ex;
        }
    }

    public ItemEntity updateItemTitleById(Object primaryKey, String newTitle){
        EntityTransaction entityTransaction = _entityManager.getTransaction();
        try {
            ItemEntity item = _entityManager.find(ItemEntity.class, primaryKey);
            if (item != null){
                entityTransaction.begin();
                item.setTitle(newTitle);
                entityTransaction.commit();
            }
            return item;
        }catch(Exception ex){
            entityTransaction.rollback();
            throw ex;
        }
    }

    public void removeUserById(Object primaryKey){
        EntityTransaction entityTransaction = _entityManager.getTransaction();
        try {
            UserEntity user = _entityManager.find(UserEntity.class, primaryKey);
            if (user != null){
                System.out.println(">> Removing User ...");
                entityTransaction.begin();
                _entityManager.remove(user);
                entityTransaction.commit();
                System.out.println(">> Removed User whith ID : " + user.getUserId());
            }
        }catch(Exception ex){
            entityTransaction.rollback();
            throw ex;
        }
    }

    public void removeItemById(Object primaryKey){
        EntityTransaction entityTransaction = _entityManager.getTransaction();
        try {
            ItemEntity item = _entityManager.find(ItemEntity.class, primaryKey);
            if (item != null){
                entityTransaction.begin();
                _entityManager.remove(item);
                entityTransaction.commit();
            }
        }catch(Exception ex){
            entityTransaction.rollback();
            throw ex;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (_entityManager != null) {
                _entityManager.close();
            }
        }catch(Throwable t){
            throw t;
        }finally {
            super.finalize();
        }
    }
}

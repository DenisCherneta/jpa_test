package ru.dcherneta.vcpe.lib.db.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by DCherneta on 09.02.2017.
 */
public class EntityDBManager {
    private static EntityDBManager _ourInstance = new EntityDBManager();

    private static EntityManagerFactory _entityManagerFactory = Persistence.createEntityManagerFactory("vcpeTestDB");;

    public static EntityDBManager getInstance() {
        return _ourInstance;
    }

    private EntityDBManager() {
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return _entityManagerFactory;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if(_entityManagerFactory != null){
                _entityManagerFactory.close();
            }
        }finally {
            super.finalize();
        }
    }

}

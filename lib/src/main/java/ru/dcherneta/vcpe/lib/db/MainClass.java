package ru.dcherneta.vcpe.lib.db;

import ru.dcherneta.vcpe.lib.db.model.ItemEntity;
import ru.dcherneta.vcpe.lib.db.model.UserEntity;
import ru.dcherneta.vcpe.lib.db.util.EntityDBActions;

import java.util.List;

//import static EntityDBAction.removeUserById;

/**
 * Created by DCherneta on 07.02.2017.
 */

public class MainClass {
    public static void main (String args[]){
        EntityDBActions entityDBActions = new EntityDBActions();
        /*UserEntity userNew = new UserEntity("PapijTEST");
        ItemEntity itemNew;// = new ItemEntity("itemTEST", user);

        for (int i=0; i<3; i++){
            itemNew = new ItemEntity();
            itemNew.setTitle("Item" + i);
            userNew.addUserItem(itemNew);
        }
        userNew = entityDBActions.insertEntity(userNew);*/

        List<UserEntity> list = entityDBActions.getUsersByName("Chack Norris");
        for (UserEntity user : list){
            System.out.println(">> "+user.toString());
            List<ItemEntity> items = user.getUserItems();
            if (!items.isEmpty()){
                for (ItemEntity item: items) {
                    System.out.println(">> "+item.toString());
                }
            }
        }

        // Update data by id
        /*user = new UserEntity("PapijTEST");
        user = entityDBActions.insertEntity(user);
        if (user == null) {
            System.out.println(">> User is null");
        }else{
            System.out.println(">> User ID = " + user.getUserId());
            //update added User
            user = entityDBActions.updateUserNameById(user.getUserId(), user.getName() + " updated");
            //remove added User
            entityDBActions.removeUserById(user.getUserId());
        }
        user = new UserEntity("PapijTEST");
        for (int i=0; i<3; i++){
            item = new ItemEntity();
            item.setTitle("Item" + i);
            user.addUserItem(item);
        }
        user = EntityDBAction.insertEntity(user);
        if (user == null) {
            System.out.println(">> User is null");
        }else{
            System.out.println(">> User ID = " + user.getUserId());
            //update added User
            user = entityDBActions.updateUserNameById(user.getUserId(), user.getName() + " updated");
            //remove added User
            entityDBActions.removeUserById(user.getUserId());
        }*/

/*
        try {
            entityDBActions.removeUserById(16L);
        }catch(Exception ex){
            System.out.print(">> Error: " + ex);
        }*/

        /*
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vcpeTestDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        //ArrayList<ItemEntity> items = new ArrayList<>(2);
        try {
            for (int i=4; i<5; i++){
                item = new ItemEntity();
                item.setTitle("Item" + i);
                user.addUserItem(item);
            }
            //user.setUserItems(items);

            entityTransaction.begin();
            entityManager.persist(user);
            entityTransaction.commit();

            for(Map.Entry entry: entityManager.getProperties().entrySet()){
                System.out.println(entry.getKey() + " ; " + entry.getValue());
            }
           entityTransaction.begin();
            entityManager.persist(item);
            entityTransaction.commit();
                    }catch(Exception ex){
            //entityTransaction.rollback();
            System.out.println("Error: " + ex);
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }*/

    }
}

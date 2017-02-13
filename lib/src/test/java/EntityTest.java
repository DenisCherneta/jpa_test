/**
 * Created by DCherneta on 09.02.2017.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.dcherneta.vcpe.lib.db.dictionary.RoleEnum;
import ru.dcherneta.vcpe.lib.db.model.ItemEntity;
import ru.dcherneta.vcpe.lib.db.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityTest {
    private EntityManagerFactory _entityManagerFactory;
    private EntityManager _entityManager;
    private EntityTransaction _transaction;

    @Before
    public void createEntityManager() {
        _entityManagerFactory = Persistence.createEntityManagerFactory("testVcpePU");
        _entityManager = _entityManagerFactory.createEntityManager();
        _transaction = _entityManager.getTransaction();
    }

    /**
     * Test for JPA entities
     */
    @Test
    public void testEntities() { //TODO add new entitites
        // UserEntity
        UserEntity user = new UserEntity("Jonik"), userDB;
        Assert.assertNotNull(">> Is Null: user", user);
        Assert.assertNotNull(">> Is Null: User ID for " + user.getName(), user.getUserId());
        persist(user);
        userDB = _entityManager.find(UserEntity.class, 1L);
        Assert.assertNotNull(">> Is Null: UserDB ", user);
        Assert.assertEquals(1, userDB.getUserId());//а зачем? если NULL то явно не 1 :-)
        userDB.setName("JonikUPDATE");
        persist(userDB);
        userDB = _entityManager.find(UserEntity.class, 1L);
        Assert.assertEquals(">> Update Error: field user.name", userDB.getName(),"JonikUPDATE");
        remove(userDB);
        userDB = _entityManager.find(UserEntity.class, 1L);
        Assert.assertNull(">> Delete Error: userDB", userDB);

        user = new UserEntity("Jonik");
        user.setUserRole(RoleEnum.ADMINISTRATOR);
        System.out.println(">> Jonik ID 1 : " + user.getUserId());
        persist(user);
        System.out.println(">> Jonik ID 2 : " + user.getUserId());

        //ItemEntity
        user = new UserEntity("JonikITEM");
        ItemEntity item, itemDB;
        for (int i=1; i<2; i++){
            item = new ItemEntity();
            item.setTitle("Item" + i);
            user.addUserItem(item);
        }
        persist(user);
        itemDB = _entityManager.find(ItemEntity.class, 1L);
        Assert.assertNotNull(">> Is Null: itemDB", itemDB);
        Long itemID = itemDB.getItemId();
        String itemTitle = itemDB.getTitle();
        itemDB.setTitle(itemTitle+"UPDATE");
        persist(itemDB);
        itemDB = _entityManager.find(ItemEntity.class, itemID);
        Assert.assertNotNull(">> Is Null: itemDB (2)", itemDB);
        Assert.assertEquals(">> Update Error: field item.title", itemDB.getTitle(),itemTitle+"UPDATE");
        remove(itemDB);
        itemDB = _entityManager.find(ItemEntity.class, itemID);
        Assert.assertNull(">> Delete Error: itemDB", itemDB);
    }

    private void persist(Object entity) {
        _transaction.begin();
        _entityManager.persist(entity);
        _transaction.commit();
        _entityManager.clear();
        _entityManager.getEntityManagerFactory().getCache().evictAll();
    }

    private void remove(Object entity) {
        _transaction.begin();
        _entityManager.remove(entity);
        _transaction.commit();
        _entityManager.clear();
        _entityManager.getEntityManagerFactory().getCache().evictAll();
    }
}

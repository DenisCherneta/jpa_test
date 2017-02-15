package ru.dcherneta.vcpe.lib.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.dcherneta.vcpe.lib.db.dictionary.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DCherneta on 06.02.2017.
 */
@Entity
@Table(name = "USER")
@NamedQueries({
        @NamedQuery(name = "UserEntity.getAll", query = "SELECT u FROM UserEntity u" ),
        @NamedQuery(name = "UserEntity.findById", query = "SELECT u FROM UserEntity u WHERE u._userId = :id" ),
        @NamedQuery(name = "UserEntity.findByName", query = "SELECT u FROM UserEntity u WHERE u._name = :name" )
})
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, unique = true, nullable = false)
    private long _userId;

    @Column(name = "name", nullable = false, length = 25)
    private String _name = null;

    @OneToMany(mappedBy = "_user", cascade = CascadeType.PERSIST)
    private List<ItemEntity> _userItems;

    @Column(name = "role_id", nullable = false/*, referencedColumnName = "role_id"*/)
    private RoleEnum _userRole;

    public UserEntity() {}

    public UserEntity(String name, List<ItemEntity> userItems) {
        setName(name);
        setUserItems(userItems);
    }

    public UserEntity(String name) {
        setName(name);
    }

    @JsonIgnore
    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        this._userId = userId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public List<ItemEntity> getUserItems() {
        if (_userItems == null){
            _userItems = new ArrayList<>();
        }
        return this._userItems;
    }

    public void setUserItems(List<ItemEntity> userItems) {
        this._userItems = userItems;
    }

    public ItemEntity addUserItem(ItemEntity item){
        getUserItems().add(item);
        item.setUser(this);
        return item;
    }

    public ItemEntity removeUserItem(ItemEntity item){
        getUserItems().remove(item);
        item.setUser(null);
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (_userId != that._userId) return false;
        if (_name != null ? !_name.equals(that._name) : that._name != null) return false;
        if (_userRole != that._userRole) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (_userId ^ (_userId >>> 32));
        result = 31 * result + (_name != null ? _name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "_userId=" + _userId +
                ", _name='" + _name +
                //'\'' + ", _userItems=" + _userItems +
                '}';
    }

    public RoleEnum getUserRole() {
        return _userRole;
    }

    public void setUserRole(RoleEnum role) {
        this._userRole = role;
    }
}

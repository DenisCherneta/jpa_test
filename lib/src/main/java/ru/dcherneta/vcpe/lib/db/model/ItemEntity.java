package ru.dcherneta.vcpe.lib.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Created by DCherneta on 06.02.2017.
 */
@Entity
@Table(name = "ITEM")
@NamedQueries({
        @NamedQuery(name = "ItemEntity.getAll", query = "SELECT i FROM ItemEntity i" ),
        @NamedQuery(name = "ItemEntity.findById", query = "SELECT i FROM ItemEntity i WHERE i._itemId = :id" ),
        @NamedQuery(name = "ItemEntity.findAllByUserId", query = "SELECT i FROM ItemEntity i WHERE i._user = :id" ),
        @NamedQuery(name = "ItemEntity.findByTitle", query = "SELECT i FROM ItemEntity i WHERE i._title = :title" )
})
public class ItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", updatable = false, unique = true, nullable = false)
    private long _itemId;

    @Column(name = "title", nullable = false, length = 25)
    private String _title;
    //private long userId;

    @ManyToOne
    @JoinColumn(name = "user_id"/*, nullable = false*/)
    private UserEntity _user;

    public ItemEntity(){}

    public ItemEntity(String title){
        this._title = title;
    }

    public ItemEntity(String title, UserEntity user) {
        this._title = title;
        this._user = user;
    }

    public long getItemId() {
        return _itemId;
    }

    public void setItemId(long itemId) {
        this._itemId = itemId;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    @JsonIgnore // this Anno work!
    public UserEntity getUser() {
        return _user;
    }

    public void setUser(UserEntity user) {
        this._user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (_itemId != that._itemId) return false;
        if (_user == null) {
            if (that._user != null)
                return false;
        }else if (!_user.equals(that._user))
            return false;
        if (_title != null ? !_title.equals(that._title) : that._title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (_itemId ^ (_itemId >>> 32));
        result = 31 * result + (_title != null ? _title.hashCode() : 0);
        //result = prime * result + ((_customer == null) ? 0 : _customer.hashCode());
        result = 31 * result + ((_user == null) ? 0 : _user.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "->> ItemEntity{" +
                "_itemId=" + _itemId +
                ", _title='" + _title + '\'' +
                ", _user=" + _user +
                '}';
    }
}

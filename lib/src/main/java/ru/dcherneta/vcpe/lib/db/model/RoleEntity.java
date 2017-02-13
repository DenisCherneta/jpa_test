package ru.dcherneta.vcpe.lib.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by DCherneta on 09.02.2017.
 */
@Entity
@Table(name = "ROLE")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "role_id", updatable = false, unique = true, nullable = false)
    private long _roleId;

    @Column(name = "name", nullable = false, length = 20)
    private String _name;

    /*@OneToMany(mappedBy = "_userRole")
    private List<UserEntity> _users;

    public List<UserEntity> getUsers() {
        return _users;
    }

    public void setUsers(List<UserEntity> users) {
        this._users = users;
    }*/

    public long getRoleId() {
        return _roleId;
    }

    public void setRoleId(long roleId) {
        this._roleId = roleId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return _roleId == that._roleId &&
                Objects.equals(_name, that._name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_roleId, _name);
    }
}

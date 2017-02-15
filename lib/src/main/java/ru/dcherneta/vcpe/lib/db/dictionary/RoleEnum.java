package ru.dcherneta.vcpe.lib.db.dictionary;

import ru.dcherneta.vcpe.lib.db.model.RoleEntity;

/**
 * Created by DCherneta on 09.02.2017.
 */
public enum RoleEnum {
    /**
     * DEFAULT VALUE
     */

    /*USER,

    ADMINISTRATOR;*/

    USER(1),

    ADMINISTRATOR(2);

    private Integer _id;

    private RoleEnum(int id){
        setId(id);
    }

    /**
     * @return {@link RoleEntity} id
     */
    public int getId() {
        if (_id == null) {
            throw new IllegalStateException(">> Field ID is not set for " + name());
        }
        return _id;
    }

    /**
     * @param id {@link RoleEntity} id to set
     */
    public void setId(int id) {
        if (_id != null) {
            throw new IllegalArgumentException(">> Field ID is already set for " + name());
        }
        this._id = id;
    }

    /**
     * Get instance of {@link RoleEnum} from its id
     *
     * @param id
     *            id
     * @return instance of {@link RoleEnum}
     */
    public static RoleEnum getStatus(int id) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }

}

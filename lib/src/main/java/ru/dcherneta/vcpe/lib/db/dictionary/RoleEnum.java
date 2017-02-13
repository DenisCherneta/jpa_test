package ru.dcherneta.vcpe.lib.db.dictionary;

import ru.dcherneta.vcpe.lib.db.model.RoleEntity;

/**
 * Created by DCherneta on 09.02.2017.
 */
public enum RoleEnum {
    /**
     * DEFAULT VALUE
     */
    USER,

    ADMINISTRATOR;

    private Integer _id;

    /**
     * @return {@link RoleEntity} id
     */
    public int getId() {
        if (_id == null) {
            throw new IllegalStateException("Field id is not set for " + name());
        }
        return _id;
    }

    /**
     * @param id {@link RoleEntity} id to set
     */
    public void setId(int id) {
        if (_id != null) {
            throw new IllegalArgumentException("Field id is already set for " + name());
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
        for (RoleEnum status : RoleEnum.values()) {
            if (status.getId() == id) {
                return status;
            }
        }

        return null;
    }

}

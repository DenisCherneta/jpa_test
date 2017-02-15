package ru.dcherneta.vcpe.lib.db.converter;

import ru.dcherneta.vcpe.lib.db.dictionary.RoleEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by DCherneta on 15.02.2017.
 */
/**
 * Converter for work with {@link RoleEnum}
 */
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<RoleEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RoleEnum attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getId();
    }

    @Override
    public RoleEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return RoleEnum.getStatus(dbData);
    }
}

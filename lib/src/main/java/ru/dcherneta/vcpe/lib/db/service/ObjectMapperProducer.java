package ru.dcherneta.vcpe.lib.db.service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.*;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.text.SimpleDateFormat;

/**
 * Jackson object mapper producer
 */
@Singleton
@Lock(LockType.READ)
public class ObjectMapperProducer {

    private ObjectMapper _mapper;
    private ObjectMapper _openStackMapper;

    public ObjectMapperProducer() {
        _mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setSerializationInclusion(Include.NON_NULL)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        _openStackMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setSerializationInclusion(Include.NON_NULL)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    /**
     * @return {@link ObjectMapper} instance
     */
    public ObjectMapper getObjectMapper() {
        return _mapper;
    }

    /**
     * @return {@link ObjectWriter} instance
     */
    public ObjectWriter getObjectWriter() {
        return _mapper.writer();
    }

    /**
     * @return {@link ObjectReader} instance
     */
    public ObjectReader getObjectReader(Class<?> type) {
        return _mapper.reader(type);
    }

    /**
     * @return {@link ObjectMapper} instance for Openstack API
     */
    public ObjectMapper getOpenStackMapper() {
        return _openStackMapper;
    }



}

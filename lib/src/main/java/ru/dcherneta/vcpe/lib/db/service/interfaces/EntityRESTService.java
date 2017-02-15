package ru.dcherneta.vcpe.lib.db.service.interfaces;

import javax.ws.rs.core.Response;

/**
 * Created by DCherneta on 14.02.2017.
 */
public interface EntityRESTService<T> {

    public default T create(T entity){
        throw new UnsupportedOperationException("Method delete is NOT supporting");
    };

    public default T delete(Long id){
        throw new UnsupportedOperationException("Method DELETE is NOT supporting");
    };

    public default T get(Long id){
        throw new UnsupportedOperationException("Method GET is NOT supporting");
    } ;
/*
    public default Response get(int id){
        throw new UnsupportedOperationException("Method GET is NOT supporting");
    } ;*/

    public default T update(T entity){
        throw new UnsupportedOperationException("Method UPDATE is NOT supporting");
    };
}

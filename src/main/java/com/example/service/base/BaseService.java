package com.example.service.base;

import org.springframework.http.ResponseEntity;

/**
 * @author Suxrob Sattorov, Fri 10:55 PM. 7/1/2022
 */
public interface BaseService<T, A> {

    ResponseEntity<?> save( T t );

    ResponseEntity<?> get( A id );

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete( A id );
}

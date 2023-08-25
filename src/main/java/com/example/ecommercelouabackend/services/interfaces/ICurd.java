package com.example.ecommercelouabackend.services.interfaces;

import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;

import java.util.List;

public interface ICurd<T> {

    T add(T t) throws NotFoundEntityException;
    void delete(long id);

    void update(T t);

    T get(long id) throws NotFoundEntityException;
    List<T> getAll();

}

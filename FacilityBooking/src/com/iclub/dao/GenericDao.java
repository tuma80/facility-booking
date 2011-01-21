package com.iclub.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T,PK extends Serializable> {
    T create(T t);
    T read(PK id,Class<T> c);
    T update(T t);
    void delete(T t);
    List<T> getAll(Class<T> c);
    List<T> getByFilter(T filter);
}

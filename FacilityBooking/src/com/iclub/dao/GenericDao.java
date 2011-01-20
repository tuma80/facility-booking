package com.iclub.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T,PK extends Serializable> {
    T create(T t);
    T read(PK id,Class<T> c);
    T update(T t);
    void delete(T t);
    public List<T> getAll(Class<T> c);
}

package com.codeWithArslan.DAO;

import java.util.List;

public interface Database<T, K, A> {
    boolean insert(T obj);
    boolean update(K updatedVal, K factor, K key);
    boolean delete(K key);
    A get(K key);
    A getAll();
}

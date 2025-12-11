package com.codeWithArslan.DAO;

import java.util.List;

public interface Database<T, K, A> {
    int insert(T obj);
    int update(K updatedVal, K factor, K key);
    int delete(K key);
    A get(K key);
    A getAll();
}

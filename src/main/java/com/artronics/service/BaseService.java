package com.artronics.service;

public interface BaseService<T> {
    T create(T entity);
}

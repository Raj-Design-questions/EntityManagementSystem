package com.rah.ems.repository;

public interface EMSRepository<T> {
    T findByUuid(String uuid);
}

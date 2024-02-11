package com.jaken.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Config {
    public EntityManager initializer() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("payment");
        return entityManagerFactory.createEntityManager();
    }
}

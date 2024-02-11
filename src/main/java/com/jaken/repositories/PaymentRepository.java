package com.jaken.repositories;

import com.jaken.database.Config;
import com.jaken.entity.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class PaymentRepository {

    private EntityManager entityManager;
    private EntityTransaction transaction;

    public PaymentRepository(Config config) {
        this.entityManager = config.initializer();
        this.transaction = this.entityManager.getTransaction();
    }

    public void createPayment(Payment payment) {
        try {
            this.transaction.begin();
            this.entityManager.persist(payment);
            this.transaction.commit();
            System.out.println("Successfully added data!");
        } catch (Exception e) {
            if (this.transaction.isActive()) {
                this.transaction.rollback();
            }
            System.out.println("Error adding data: " + e.getMessage());
            throw e;
        }
    }

    public List<Payment> findAllPayments() {
        return entityManager.createQuery("SELECT p FROM Payment p", Payment.class).getResultList();
    }

    public Integer deletePayment(Integer studentId) {
        try {
            this.transaction.begin();
            Object existData = this.entityManager.find(Payment.class, studentId);
            if(existData == null){
                 return 1;
            }
            this.entityManager.remove(existData);
            System.out.println("Successfully deleted data!");
            this.transaction.commit();
            return 0;
        } catch (Exception e) {
            if (this.transaction.isActive()) {
                this.transaction.rollback();
            }
            System.out.println("Error deleting data: " + e.getMessage());
            throw e;
        }
    }

    public void updatePayment(Payment payment) {
        try {
            this.transaction.begin();
            entityManager.merge(payment);
            this.transaction.commit();
            System.out.println("Successfully updated data!");
        } catch (Exception e) {
            if (this.transaction.isActive()) {
                this.transaction.rollback();
            }
            System.out.println("Error updating data: " + e.getMessage());
            throw e;
        }
    }
}

package project.app.persistence;

import org.springframework.data.repository.CrudRepository;

import project.app.model.Payment;

/**
 * PaymentRepository
 */
public interface PaymentRepository extends CrudRepository<Payment, Integer>{

    
}
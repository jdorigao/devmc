package br.com.jdorigao.devmc.repositories;

import br.com.jdorigao.devmc.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}

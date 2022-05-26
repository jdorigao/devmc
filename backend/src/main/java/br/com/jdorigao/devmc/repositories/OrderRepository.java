package br.com.jdorigao.devmc.repositories;

import br.com.jdorigao.devmc.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}

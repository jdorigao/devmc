package br.com.jdorigao.devmc.repositories;

import br.com.jdorigao.devmc.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}

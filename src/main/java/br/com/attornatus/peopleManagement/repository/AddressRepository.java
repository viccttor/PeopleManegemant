package br.com.attornatus.peopleManagement.repository;

import br.com.attornatus.peopleManagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}

package br.com.attornatus.peopleManagement.repository;

import br.com.attornatus.peopleManagement.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

}

package org.gabrielbarrilli.restwithspringbootandjavaerudio.repository;

import org.gabrielbarrilli.restwithspringbootandjavaerudio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
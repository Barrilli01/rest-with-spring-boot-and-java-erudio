package org.gabrielbarrilli.restwithspringbootandjavaerudio.service;

import jakarta.persistence.EntityNotFoundException;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.model.Person;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Achando uma pessoa!");


        return personRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Não há pesssoa com esse id!"));
    }

    public Person create(Person person) {
        logger.info("Creating one person!");

        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person!");

        var pessoa = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        pessoa.setFirstName(person.getFirstName());
        pessoa.setLastName(person.getLastName());
        pessoa.setAddress(pessoa.getAddress());
        pessoa.setGender(person.getGender());

        return personRepository.save(person);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var deletar = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        personRepository.delete(deletar);
    }
}

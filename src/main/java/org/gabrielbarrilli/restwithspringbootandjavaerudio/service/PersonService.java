package org.gabrielbarrilli.restwithspringbootandjavaerudio.service;

import jakarta.persistence.EntityNotFoundException;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.controller.PersonController;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.mapper.DozerMapper;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.model.Person;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.repository.PersonRepository;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.data.vo.PersonVO;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn ;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonVO> findAll() {

        var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));

        return persons;
    }

    public PersonVO findById(Long id) {
        logger.info("Achando uma pessoa!");

        var person = personRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Não há pesssoa com esse id!"));

        var vo =  DozerMapper.parseObject(person, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating one person!");

        var person = DozerMapper.parseObject(personVO, Person.class);

        var vo = DozerMapper.parseObject(personRepository.save(person), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }

    public PersonVO update(PersonVO personVO) {
        logger.info("Updating one person!");

        var person = personRepository.findById(personVO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setAddress(personVO.getAddress());
        person.setGender(personVO.getGender());

        var vo = DozerMapper.parseObject(personRepository.save(person), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var deletar = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        personRepository.delete(deletar);
    }
}

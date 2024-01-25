package org.gabrielbarrilli.restwithspringbootandjavaerudio.service;

import jakarta.persistence.EntityNotFoundException;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.mapper.DozerMapper;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.model.Person;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.repository.PersonRepository;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
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

    public List<PersonVO> findAll() {

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        logger.info("Achando uma pessoa!");

        var person = personRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Não há pesssoa com esse id!"));

        return DozerMapper.parseObject(person, PersonVO.class);
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating one person!");

        var person = DozerMapper.parseObject(personVO, Person.class);

        return DozerMapper.parseObject(personRepository.save(person), PersonVO.class);
    }

    public PersonVO update(PersonVO personVO) {
        logger.info("Updating one person!");

        var pessoa = personRepository.findById(personVO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        pessoa.setFirstName(personVO.getFirstName());
        pessoa.setLastName(personVO.getLastName());
        pessoa.setAddress(pessoa.getAddress());
        pessoa.setGender(personVO.getGender());

        return DozerMapper.parseObject(personRepository.save(pessoa), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var deletar = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

        personRepository.delete(deletar);
    }
}

package org.gabrielbarrilli.restwithspringbootandjavaerudio.controller;

import org.gabrielbarrilli.restwithspringbootandjavaerudio.service.PersonService;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.data.vo.PersonVO;
import org.springframework.web.bind.annotation.*;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.util.MediaType;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<PersonVO> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping("/criarPessoa")
    public PersonVO create(@RequestBody PersonVO personVO) {
        return personService.create(personVO);
    }

    @PutMapping("/atualizarPessoa")
    public PersonVO update(@RequestBody PersonVO personVO) {
        return personService.update(personVO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

}

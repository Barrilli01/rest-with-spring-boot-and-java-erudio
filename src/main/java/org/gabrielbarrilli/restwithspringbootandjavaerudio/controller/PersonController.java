package org.gabrielbarrilli.restwithspringbootandjavaerudio.controller;

import org.gabrielbarrilli.restwithspringbootandjavaerudio.service.PersonService;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.vo.v1.PersonVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}")
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

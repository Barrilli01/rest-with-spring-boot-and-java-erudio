package org.gabrielbarrilli.restwithspringbootandjavaerudio.controller;

import org.gabrielbarrilli.restwithspringbootandjavaerudio.model.Person;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.service.PersonService;
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
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping("/criarPessoa")
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping("/atualizarPessoa")
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

}

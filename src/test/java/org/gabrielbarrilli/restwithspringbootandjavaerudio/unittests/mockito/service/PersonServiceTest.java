package org.gabrielbarrilli.restwithspringbootandjavaerudio.unittests.mockito.service;

import org.gabrielbarrilli.restwithspringbootandjavaerudio.model.Person;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.repository.PersonRepository;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.service.PersonService;
import org.gabrielbarrilli.restwithspringbootandjavaerudio.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith((MockitoExtension.class))
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPerson();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        Person person = input.mockEntity();
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
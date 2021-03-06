package com.waracle.cakemgr.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4201", "https://angular-spring-frontend.herokuapp.com", "https://angular-spring-frontend.matthewdavis.io"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/clients")
public class ClientsController {

    private ClientsRepository repository;

    /*
     * Class Constructor
     *
     * We pass the CakeRepository to the constructor so that dependency injection instantiates
     * this class for us. Hence the @AutoWired tag, this is what makes DI work with spring.
     *
     */
    @Autowired
    public void ClientsControlller(final ClientsRepository repository) {

        this.repository = repository;

    }

    /*
     * Retrieve all records
     *
     * This method retrieves all records in the database and returns a collection of type
     * List.
     *
     */
    @GetMapping
    public ResponseEntity<List<Client>> findAll() {

        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);

    }

    /*
     * Update a record
     *
     * This method updates a record in the database matching the same id that is passed
     * via the url path (i.e.: http://localhost:8081/cakes/123).
     *
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Long id, @RequestBody Client user) {

        user.id = id;

        return new ResponseEntity<>(repository.save(user), HttpStatus.OK);

    }

    /*
     * Create a new record
     *
     * This method creates a new record. It uses the @RequestBody annotation which parses
     * the body of the request and marshals it as a Cake object.
     *
     */
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client user) {

        return new ResponseEntity<>(repository.save(user), HttpStatus.OK);

    }

    /*
     * Delete a record
     *
     * This method deletes a record by the id passed in the url path (i.e.: http://localhost:8081/cakes/123).
     *
     * If the record does not exist matching the same id we catch the exception and then turn around
     * and return an http status code of 404.
     *
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        try {

            repository.deleteById(id);

            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException ex) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }

    }

    /*
     * Get record by id
     *
     * This method retrieves a record matching the same id (i.e.: http://localhost:8081/cakes/123).
     *
     * If the record does not exist matching the same id we catch the exception and then turn around
     * and return an http status code of 404.
     *
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> getById(@PathVariable("id") Long id) {

        try {

            Optional<Client> user = repository.findById(id);

            if(user.isPresent()) {

                return new ResponseEntity<>(user.get(), HttpStatus.OK);

            } else {

                return new ResponseEntity<>(new Client(), HttpStatus.NOT_FOUND);

            }

        } catch (EmptyResultDataAccessException ex) {

            return new ResponseEntity<>(new Client(), HttpStatus.NOT_FOUND);

        }

    }

}

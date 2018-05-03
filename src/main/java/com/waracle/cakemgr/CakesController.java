package com.waracle.cakemgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4201"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/cakes")
public class CakesController {

    private CakesRepository repository;

    /*
     * Class Constructor
     *
     * We pass the CakeRepository to the constructor so that dependency injection instantiates
     * this class for us. Hence the @AutoWired tag, this is what makes DI work with spring.
     *
     */
    @Autowired
    public void CakesService(final CakesRepository repository) {

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
    public ResponseEntity<List<Cake>>getCakes() {

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
    public ResponseEntity<Cake> updateCate(@PathVariable("id") Long id, @RequestBody Cake cake) {

        cake.id = id;

        return new ResponseEntity<>(repository.save(cake), HttpStatus.OK);

    }

    /*
     * Create a new record
     *
     * This method creates a new record. It uses the @RequestBody annotation which parses
     * the body of the request and marshals it as a Cake object.
     *
     */
    @PostMapping
    public ResponseEntity<Cake> createCake(@RequestBody Cake cake) {

        return new ResponseEntity<>(repository.save(cake), HttpStatus.OK);

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
    public ResponseEntity<?> deleteCake(@PathVariable("id") Long id) {

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
    public ResponseEntity<Cake> getCakeById(@PathVariable("id") Long id) {

        try {

            Optional<Cake> cake = repository.findById(id);

            if(cake.isPresent()) {

                return new ResponseEntity<>(cake.get(), HttpStatus.OK);

            } else {

                return new ResponseEntity<>(new Cake(), HttpStatus.NOT_FOUND);

            }

        } catch (EmptyResultDataAccessException ex) {

            return new ResponseEntity<>(new Cake(), HttpStatus.NOT_FOUND);

        }

    }

}

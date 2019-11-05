package za.co.discovery.assignment.ganesan.controller;

import za.co.discovery.assignment.ganesan.dto.Dto;
import za.co.discovery.assignment.ganesan.service.CrudService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class CrudController<T extends Dto, Id> {

    private final CrudService<T, Id> service;

    protected CrudController(CrudService<T, Id> crudService) {
        this.service = crudService;
    }


    @GetMapping("")
    protected List<T> index() {
        return service.getAll();
    }

    @GetMapping("{id}")
    protected T get(@PathVariable Id id) {
        return service.findById(id);
    }

    @PostMapping("")
    protected T post(@RequestBody @Valid T entity) {
        return service.save(entity);
    }

    @PutMapping("{id}")
    protected T put(@PathVariable Id id, @RequestBody @Valid T entity) {
        return service.update(entity);
    }

    @DeleteMapping("{id}")
    protected T delete(@PathVariable Id id) {
        return service.delete(id);
    }




}

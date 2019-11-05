package za.co.discovery.assignment.ganesan.service;

import za.co.discovery.assignment.ganesan.dto.Dto;

import java.util.List;

public interface CrudService<T extends Dto, Id> {

    List<T> saveAll(List<T> allEntity);

    List<T> getAll();

    T findById(Id id);

    T update(T entity);

    T delete(T entity);

    T save(T entity);

    T delete(Id id);
}

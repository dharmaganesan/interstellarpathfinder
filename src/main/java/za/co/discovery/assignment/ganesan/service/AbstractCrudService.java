package za.co.discovery.assignment.ganesan.service;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.discovery.assignment.ganesan.dto.Dto;
import za.co.discovery.assignment.ganesan.entity.Entity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCrudService<T extends Dto, E extends Entity, Id> implements CrudService<T, Id> {

    private final JpaRepository<E, Id> repository;

    public AbstractCrudService(JpaRepository<E, Id> repository) {
        this.repository = repository;
    }


    protected abstract T toDto(E entity);

    protected abstract E toEntity(T dto);

    @Override
    public List<T> saveAll(List<T> allEntity) {
        return repository.saveAll(allEntity.stream().map(this::toEntity).collect(Collectors.toList()))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<T> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public T findById(Id id) {
        return toDto(repository.getOne(id));
    }

    @Override
    public T update(T entity) {
        return toDto(repository.save(toEntity(entity)));
    }

    @Override
    public T delete(T entity) {
        repository.delete(toEntity(entity));
        return entity;
    }

    @Override
    public T save(T entity) {
        return toDto(repository.save(toEntity(entity)));
    }

    @Override
    public T delete(Id id) {
        T byId = findById(id);
        return delete(byId);
    }
}

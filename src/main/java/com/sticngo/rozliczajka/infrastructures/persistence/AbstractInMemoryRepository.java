package com.sticngo.rozliczajka.infrastructures.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.requireNonNull;


@Slf4j
public abstract class AbstractInMemoryRepository<E extends BaseEntity> implements Repository<E> {

  protected ConcurrentHashMap<Long, E> data;
  private AtomicLong lastId;

  public AbstractInMemoryRepository() {
    this.data = new ConcurrentHashMap<>();
    this.lastId = new AtomicLong(1);
  }

  public E findOne(Long id) {
    ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
    Class clazz = (Class) superClass.getActualTypeArguments()[0];

    return Optional.ofNullable(data.get(id))
        .orElseThrow(() -> new IllegalArgumentException(
                String.format("Entity of type: %s not found, id: %d", clazz.getName(), id)
            )
        );
  }

  public Optional<E> findById(Long id) {
    return Optional.ofNullable(data.get(id));
  }

  public List<E> findAll() {
    return new ArrayList<>(data.values());
  }

  public Page<E> findAll(Pageable pageable) {
    return new PageImpl<>(findAll(), pageable, data.size());
  }

  public E save(E entity) {
    requireNonNull(entity);
    if (isNew(entity)) {
      entity.setId(lastId.getAndIncrement());
      data.put(entity.getId(), entity);
    } else {
      data.replace(entity.getId(), entity);
    }
    return entity;
  }

  public void delete(E entity) {
    data.remove(entity.getId());
  }

  public void deleteById(Long id) {
    data.remove(id);
  }

  public boolean existsById(Long id) {
    requireNonNull(id);
    return data.containsKey(id);
  }

  private boolean isNew(E entity) {
    return entity.getId() == null;
  }

  public Long count() {
    return (long) data.values().size();
  }

  public E getById(Long id) {
    return findOne(id);
  }

}


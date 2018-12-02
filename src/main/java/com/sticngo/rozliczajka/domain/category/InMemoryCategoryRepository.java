package com.sticngo.rozliczajka.domain.category;

import com.sticngo.rozliczajka.infrastructures.persistence.AbstractInMemoryRepository;

import java.util.List;

public class InMemoryCategoryRepository
    extends AbstractInMemoryRepository<Category>
    implements CategoryRepository {

  @Override
  public List<Category> findCategoriesByUserId(Long id) {
    return null;
  }
}

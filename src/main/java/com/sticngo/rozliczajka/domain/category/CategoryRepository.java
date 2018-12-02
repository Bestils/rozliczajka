package com.sticngo.rozliczajka.domain.category;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends com.sticngo.rozliczajka.infrastructures.persistence.Repository<Category> {
  List<Category> findCategoriesByUserId(Long userId);
}
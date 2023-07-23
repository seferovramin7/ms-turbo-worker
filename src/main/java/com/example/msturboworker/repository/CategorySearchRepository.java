package com.example.msturboworker.repository;

import com.example.msturboworker.entity.CategorySearchEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorySearchRepository extends CrudRepository<CategorySearchEntity, Long> {
    List<CategorySearchEntity> findAllByChatId(Long chatId);

    List<CategorySearchEntity> findAll();

    void deleteBySearchUrl(String url);

}


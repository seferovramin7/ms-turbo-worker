package com.example.msturboworker.repository;

import com.example.msturboworker.entity.TelegramEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramRepository extends CrudRepository<TelegramEntity, Long> {
    TelegramEntity findByChatId(Long chatId);
}


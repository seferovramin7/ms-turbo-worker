package com.example.msturboworker.repository;

import com.example.msturboworker.entity.CarEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long> {
    List<CarEntity> findAllByChatId(Long chatId);

    List<CarEntity> findAll();

    CarEntity findByCarId(String carId);

    CarEntity findByCarIdAndAndChatId(String carId, Long chatId);

    void deleteCarEntityByCarId(String carId);

    void deleteCarEntityByCarIdAndChatId(String carId, Long chatId);
}


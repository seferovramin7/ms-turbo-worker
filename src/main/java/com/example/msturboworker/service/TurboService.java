package com.example.msturboworker.service;

import com.example.msturboworker.entity.CarEntity;
import com.example.msturboworker.entity.CategorySearchEntity;
import com.example.msturboworker.repository.CarRepository;
import com.example.msturboworker.repository.CategorySearchRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TurboService {

    private final CarRepository carRepository;

    private final CategorySearchRepository categorySearchRepository;

    public List<String> retrieveAllSearches(Long chatId) {
        List<CategorySearchEntity> all = categorySearchRepository.findAllByChatId(chatId);

        List<String> searchUrls = new ArrayList<>();
        for (CategorySearchEntity e : all) {
            searchUrls.add(e.getSearchUrl());
        }
        return searchUrls;
    }

    public List<String> retrieveAllCars(Long chatId) {
        List<CarEntity> allByChatId = carRepository.findAllByChatId(chatId);

        List<String> carLinks = new ArrayList<>();
        for (CarEntity e : allByChatId) {
            carLinks.add("https://turbo.az/autos/" + e.getCarId());
        }
        return carLinks;
    }

    public CategorySearchEntity saveSearch(Long chatId, String url) {
        return categorySearchRepository.save(
                CategorySearchEntity.builder().searchUrl(url).chatId(chatId).build());
    }

    public void deleteSearch(String url) {
        categorySearchRepository.deleteBySearchUrl(url);
    }

    public CarEntity saveCar(Long chatId, String carId, String price) {
        if (carRepository.findByCarId(carId) == null) {
            return carRepository.save(
                    CarEntity.builder().carId(carId).chatId(chatId).price(price).build());
        }
        return CarEntity.builder().build();
    }

    public void deleteCar(String carId) {
        carRepository.deleteByCarId(carId);
    }
}
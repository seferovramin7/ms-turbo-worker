package com.example.msturboworker.controller;

import com.example.msturboworker.service.TurboService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TurboCarController {

    private final TurboService service;

    @GetMapping("save/car")
    @CrossOrigin(origins = "https://turbo.az")
    public ResponseEntity<?> saveCar(@RequestParam String url, @RequestParam Long chatId,
                                     @RequestParam String price) {
        log.info("Car to save to database : {} by user : {}",url,chatId);
        return ResponseEntity.ok(service.saveCar(chatId, url, price));
    }

    @GetMapping("list/car")
    public ResponseEntity<?> carList(@RequestParam Long chatId) {
        return ResponseEntity.ok(service.retrieveAllCars(chatId));
    }

    @DeleteMapping("delete/car")
    public ResponseEntity<?> deleteCar(@RequestParam String url, @RequestParam Long chatId) {
        service.deleteCar(url, chatId);
        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping("save/search")
    public ResponseEntity<?> saveSearch(@RequestParam String url, @RequestParam Long chatId) {
        log.info("Search url : " + url);
        return ResponseEntity.ok(service.saveSearch(chatId, url));
    }

    @DeleteMapping("delete/search")
    public ResponseEntity<?> deleteSearch(@RequestParam String url, @RequestParam Long chatId) {
        service.deleteSearch(url, chatId);
        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping("list/search")
    public ResponseEntity<?> searchList(@RequestParam Long chatId) {
        return ResponseEntity.ok(service.retrieveAllSearches(chatId));
    }


}

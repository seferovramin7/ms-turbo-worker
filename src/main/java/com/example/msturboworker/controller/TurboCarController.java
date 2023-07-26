package com.example.msturboworker.controller;

import com.example.msturboworker.service.TurboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TurboCarController {

    private final TurboService service;

    @GetMapping("save/car")
    @CrossOrigin(origins = "https://turbo.az")
    public ResponseEntity<?> saveCar(@RequestParam String url, @RequestParam Long chatId,
                                     @RequestParam String price) {
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
        return ResponseEntity.ok(service.saveSearch(chatId, url));
    }

    @DeleteMapping("delete/search")
    public ResponseEntity<?> deleteSearch(@RequestParam String url) {
        service.deleteSearch(url);
        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping("list/search")
    public ResponseEntity<?> searchList(@RequestParam Long chatId) {
        return ResponseEntity.ok(service.retrieveAllSearches(chatId));
    }


    // 1. in my chrome extension there will be input field that user will type his userId and when he clicks to save button, it will save on chrome storage, and
    // if there customerId in storage it will be shown in input as placeholder.
    // 2. there will be a link which goes to google.com
    // 3. there will be dropdown "Search List" dropdown list, that will fetch data from backend, and there will be DELETE button which will call
    // that elements delete endpoint
    // 3. there will be dropdown "Car List" dropdown list, that will fetch data from backend, and there will be DELETE button which will call
    // that elements delete endpoint
    // 4. There will be SaveCar button, that button will take "products-i__datetime" class named data and send to backend
    // 5. there will be "Save Search" button will take pages url and send to backend


}

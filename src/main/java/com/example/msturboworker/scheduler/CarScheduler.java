package com.example.msturboworker.scheduler;

import com.example.msturboworker.client.TurboClient;
import com.example.msturboworker.entity.CarEntity;
import com.example.msturboworker.entity.CategorySearchEntity;
import com.example.msturboworker.repository.CarRepository;
import com.example.msturboworker.repository.CategorySearchRepository;
import com.example.msturboworker.telegram.send.text.SendMessageDTO;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarScheduler {

    private final CarRepository carRepository;

    private final CategorySearchRepository categorySearchRepository;

    private final TurboClient turboClient;

    @Scheduled(fixedRate = 60000)
    public void carScheduler() throws IOException {

        List<CarEntity> all = carRepository.findAll();

        for (CarEntity e : all) {
            String price = e.getPrice();
            String carId = e.getCarId();
            Long chatId = e.getChatId();
            Long id = e.getId();

            Document doc = Jsoup.connect(carId).get();
            String currentPrice = doc.getElementsByClass("tz-mt-10").text();

            if (!currentPrice.equals(price)) {
                log.info("currentPrice : {}", currentPrice);
                log.info("priceFromDatabase : {}", price);
                String text = "Qiymət yeniləndi : \n " + carId;
                carRepository.save(
                        CarEntity.builder().id(id).carId(carId).chatId(chatId).price(currentPrice)
                                .build());
                turboClient.sendMessage(
                        SendMessageDTO.builder().chatId(chatId).text(text).build());
            }
        }
    }

    @Scheduled(fixedRate = 60000)
    public void searchScheduler() throws IOException {
        List<CategorySearchEntity> all = categorySearchRepository.findAll();
        for (CategorySearchEntity e : all) {
            Long chatId = e.getChatId();
            Document doc = Jsoup.connect(e.getSearchUrl()).get();


            Elements elementsByClass = doc.getElementsByClass("products-i");

            for (Element product : elementsByClass) {
                Elements elements = product.getElementsByClass("products-i__datetime");

                String fullDate = elements.text();
                if (fullDate.contains("bugün")) {
                    String[] split = fullDate.split(" ");
                    String timeString = split[2];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime desiredTime = LocalTime.parse(timeString, formatter);

                    LocalTime now = LocalTime.now();
                    Duration elapsedTime;
                    elapsedTime = Duration.between(desiredTime, now);
                    long minutesElapsed = elapsedTime.getSeconds() / 60;
                    if (minutesElapsed <= 5) {
                        log.info("Elapsed time: " + minutesElapsed + " seconds");
                        Elements url = product.getElementsByClass("products-i__link");
                        String text = "Avtomobil əlavə olundu : turbo.az" + url.attr("href");
                        turboClient.sendMessage(
                                SendMessageDTO.builder().chatId(chatId).text(text).build());
                    }
                }
            }
        }
    }
}

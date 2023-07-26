package com.example.msturboworker.scheduler;


import com.example.msturboworker.repository.TelegramRepository;
import com.example.msturboworker.telegram.send.SendMessageResponseDTO;
import com.example.msturboworker.telegram.send.text.SendMessageDTO;
import com.example.msturboworker.telegram.update.TelegramResponseDTO;
import com.example.msturboworker.telegram.update.TelegramUpdateDTO;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BotScheduler {
    private final TelegramRepository repository;
    @Value("${telegram.api.base-url}")
    String api;
    @Value("${telegram.api.token}")
    String token;
    private Long offset = null;

//    @Scheduled(fixedRate = 1000)
    public void getUpdates() throws IOException {
        String url = api + "/bot" + token + "/getUpdates";
        if (offset != null) {
            url = url + "?offset=" + offset;
        }

        RestTemplate restTemplate = new RestTemplate();
        TelegramResponseDTO forObject = restTemplate.getForObject(url, TelegramResponseDTO.class);
        if (forObject.getResult().size() > 0) {
            if (forObject.getResult().get(0) != null) {
                TelegramUpdateDTO telegramUpdateDTO = forObject.getResult().get(0);
                offset = telegramUpdateDTO.getUpdateId() + 1;

                Long id = telegramUpdateDTO.getMessageDTO().getChat().getId();

                String response = "İstifadəçi nömrəniz: " + id;
                sendMessage(response, id);
            }
        }
    }


    public void sendMessage(String text, Long id) throws IOException {
        String url1 = api + "/bot" + token + "/sendMessage";


        SendMessageDTO dto = SendMessageDTO.builder().chatId(id).text(text).build();


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url1, dto, SendMessageResponseDTO.class);

    }

}

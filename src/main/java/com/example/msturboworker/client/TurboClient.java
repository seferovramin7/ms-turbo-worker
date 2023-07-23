package com.example.msturboworker.client;

import com.example.msturboworker.telegram.send.SendMessageResponseDTO;
import com.example.msturboworker.telegram.send.text.SendMessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "turbo", url = "https://api.telegram.org/bot6362868299:AAFvXFqSlfW_EZi-DaUWfPUp--2Qkq6iFME/")
public interface TurboClient {

    @RequestMapping(method = RequestMethod.GET, value = "sendMessage")
    SendMessageResponseDTO sendMessage(SendMessageDTO messageDTO);

}
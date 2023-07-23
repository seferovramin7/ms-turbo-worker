package com.example.msturboworker.telegram.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelegramResponseDTO {

    @JsonProperty("ok")
    private Boolean ok;

    @JsonProperty("result")
    private List<TelegramUpdateDTO> result;

    @JsonProperty("error_code")
    private Long errorCode;

    @JsonProperty("parameters")
    private ParameterDTO parameters;

}

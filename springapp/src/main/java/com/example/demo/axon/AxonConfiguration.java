package com.example.demo.axon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfiguration {

    @Bean
    @Primary
    public Serializer axonJacksonSerializer(ObjectMapper objectMapper) {
        return JacksonSerializer.builder()
                .objectMapper(objectMapper)
                .defaultTyping()
                .build();
    }
}

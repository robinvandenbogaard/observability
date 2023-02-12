package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
class TodoDTOTest {

    @Test
    void serialization() throws JsonProcessingException {
        var m = new ObjectMapper();

        var list = List.of(new TodoDTO(UUID.fromString("1633d5d5-d569-4ce0-bf14-adb168fb6ae3"), "banaan", false));
        var expected = """
                [{"uuid":"1633d5d5-d569-4ce0-bf14-adb168fb6ae3","summary":"banaan","completed":false}]""";

        assertThat(m.writeValueAsString(list), is(expected));

    }
  
}
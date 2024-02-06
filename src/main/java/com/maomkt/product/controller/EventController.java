package com.maomkt.product.controller;

import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maomkt.product.StreamLambdaHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableWebMvc
@Slf4j
public class EventController {

    private final ObjectMapper objectMapper = LambdaContainerHandler.getObjectMapper();
    private final String scheduledEvent = StreamLambdaHandler.AnnotatedScheduledEvent.class.getName();


    @RequestMapping(path = "/event",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> handleEvent(@RequestParam("type") String type, @RequestBody String request) throws JsonProcessingException {
        if (scheduledEvent.contentEquals(type)) {
            log.debug("Lambda Keep Alive Event Received");
        } else {
            log.error("Unknown event received: {}", type);
        }

        Map<String, String> result = new HashMap<>();
        result.put("status", "OK");
        return result;
    }

    private String parseEventBody(String request) throws JsonProcessingException {
        Map<String, Object> raw = (Map<String, Object>) objectMapper.readValue(request, Map.class);

        // Peek inside one event
        if (raw.get("Records") instanceof List) {
            List<Map<String, Object>> events = (List<Map<String, Object>>) raw.get("Records");
            if (events.size() > 0) {
                raw = events.get(0);
            } else {
                log.warn("Empty, dummy event records {}", events);
                return "";
            }
        }

        return raw.get("body").toString();
    }
}

package server_controller.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server_controller.dto.ErrorLog;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("kafka")
@RestController
@RequiredArgsConstructor
public class kafkaTestController {
    private final KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("error")
    public String errorProducer(@RequestParam(defaultValue = "Critical Database Connection Error") String message){
        ErrorLog event = new ErrorLog(
                UUID.randomUUID().toString(),
                "payment-service",
                "ERROR",
                message,
                LocalDateTime.now(),
                Map.of("host", "prod-db-01", "region", "ap-northeast-2")
        );
        kafkaTemplate.send("system-logs",event);
        return "";
    }
}

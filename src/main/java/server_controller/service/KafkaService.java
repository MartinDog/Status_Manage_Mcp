package server_controller.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import server_controller.dto.ErrorLog;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaService {
    private final SentinelService sentinelService;
    /*
    * 시스템 로그를 받아
    * */
    @KafkaListener(topics = "system-logs", groupId = "sentinel-agent-group")
    public void systemLogConsumer(ErrorLog errorLog){
        log.info("Error log 수신");
        sentinelService.analyzeError(errorLog);
    }

}

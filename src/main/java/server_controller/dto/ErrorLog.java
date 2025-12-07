package server_controller.dto;

import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @param traceId 트랜잭션 추적용 ID (필수)
 * @param serviceName 어떤 마이크로서비스에서 났는지
 * @param logLevel INFO, WARN, ERROR
 * @param message 오류 내용
 * @param timestamp  발생 시간
 * @param metadata 추가 정보
 *
 */
public record ErrorLog(String traceId,
                       String serviceName,
                       String logLevel,
                       String message,
                       LocalDateTime timestamp,
                       Map<String, String> metadata
)
{
    public ErrorLog{

    }
}

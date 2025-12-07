package server_controller.dto;

import java.util.List;

/**
 *  ai 학습용 데이터
 * @param id    id
 * @param errorPattern  에러 키워드
 * @param rootCause 원인 설명
 * @param solution 해결법
 * @param tags  태그들
 */
public record ErrorKnowledge(
        String id,
        String errorPattern,
        String rootCause,
        String solution,
        List<String> tags
) {
}

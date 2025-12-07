package server_controller.dto;

/**
 *
 * @param summary 요약
 * @param rootCause 원인
 * @param level 심각도
 * @param recommendedAction AI 추천 해결법
 * @param confidence AI 의 확신도
 */
public record AiResult(
        String summary,
        String rootCause,
        String level,
        String recommendedAction,
        double confidence
) {
}

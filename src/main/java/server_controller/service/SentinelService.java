package server_controller.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import server_controller.dto.AiResult;
import server_controller.dto.ErrorLog;

import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class SentinelService {
    private final ChatClient chatClient;

    public void analyzeError(ErrorLog errorLog){
        String promptText =
                """
                        Analyze the following log 
                         - Service: {serviceName}
                         - Level: {logLevel}
                         - Message: {message}
                         - Metadata: {metadata}
                        Provide the output strictly in JSON format matching the AiResult schema.
                """;
        PromptTemplate promptTemplate = new PromptTemplate(promptText);
        Prompt prompt = promptTemplate.create(
                Map.of(
                       "serviceName",errorLog.serviceName()
                        ,"logLevel",errorLog.logLevel()
                        ,"message",errorLog.message()
                        ,"metadata",errorLog.metadata()
                )
        );
        AiResult aiResult = chatClient.prompt(prompt)
                .call()
                .entity(AiResult.class);
        log.info(aiResult.recommendedAction());
        log.info(aiResult.summary());
        log.info(String.valueOf(aiResult.confidence()));
    }
}

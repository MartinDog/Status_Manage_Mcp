package server_controller.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        return builder
                .defaultSystem("""
                You are Sentinel-AI, an elite Site Reliability Engineer.
                Your job is to analyze system logs, identify root causes, and suggest actionable fixes.
                
                Guidelines:
                1. Be concise and technical.
                2. If the log is critical, suggest immediate mitigation.
                3. Analyze the provided log context carefully.
                """)
                .build();
    }

}

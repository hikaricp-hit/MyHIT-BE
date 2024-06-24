package com.example.projectbase.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "question")
@Component
@Getter
@Setter
public class QuestionProperties {
    private String id;
    private String content;
    private String status;
    private String answer;
    private String askDate;
    private String responseDate;
}


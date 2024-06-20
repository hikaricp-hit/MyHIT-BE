package com.example.projectbase.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@ConfigurationProperties(prefix = "notification")
@Component
@Getter
@Setter
public class NotificationProperties {
    private String id;
    private String name;
    private String detail;
    private String sendDate;
}

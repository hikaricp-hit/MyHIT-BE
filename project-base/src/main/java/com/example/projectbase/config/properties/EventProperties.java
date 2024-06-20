package com.example.projectbase.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@ConfigurationProperties(prefix = "event")
@Component
@Getter
@Setter
public class EventProperties {
    private String id;
    private String name;
    private String type;
    private String location;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
}

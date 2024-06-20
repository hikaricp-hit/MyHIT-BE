package com.example.projectbase.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@ConfigurationProperties(prefix = "course")
@Component
@Getter
@Setter
public class CourseProperties {
    private String id;
    private String name;
    private String detail;
    private String leader;
    private String startDate;
    private String picture;
    private String status;
}

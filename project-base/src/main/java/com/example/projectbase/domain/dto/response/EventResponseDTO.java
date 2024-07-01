package com.example.projectbase.domain.dto.response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResponseDTO {
    private String id;
    private String name;
    private String type;
    private String location;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
}

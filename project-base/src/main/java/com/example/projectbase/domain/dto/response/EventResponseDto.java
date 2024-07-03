package com.example.projectbase.domain.dto.response;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventResponseDto {
    //private String id;
    private String name;
    private String type;
    private String location;
    private Date startDate;
    private Date endDate;
    private Date startTime;
    private Date endTime;
}

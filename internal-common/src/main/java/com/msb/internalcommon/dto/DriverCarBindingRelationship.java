package com.msb.internalcommon.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DriverCarBindingRelationship {

    private Long id;
    private Long driverId;
    private Long carId;
    private Integer bindingState;
    private LocalDateTime bindingTime;
    private LocalDateTime unBindingTime;
}

package com.msb.internalcommon.dto;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DriverUserWorkStatus {
    private Long id;
    private Long driverId;
    private Integer workStatus;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}

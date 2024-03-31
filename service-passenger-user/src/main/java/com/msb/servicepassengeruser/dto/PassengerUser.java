package com.msb.servicepassengeruser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: PassengerUser
 * Package: com.msb.servicepassengeruser.dto
 * Description: passenger_user表的实体类
 *
 * @Author Emoaya
 * @Create 2024/3/30 21:13
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerUser {
    private int id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private String passengerPhone;
    private String passengerName;
    private byte passengerGender;
    private byte state;

}

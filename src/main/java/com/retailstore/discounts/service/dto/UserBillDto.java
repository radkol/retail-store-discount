package com.retailstore.discounts.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
public class UserBillDto {

    private UserDto userDto;
    private BillDto billDto;

}

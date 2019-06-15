package com.retailstore.discounts.service.dto;

public class DiscountProcessorDto {

    private final UserDto userDto;
    private final BillDto billDto;

    public DiscountProcessorDto(UserDto userDto, BillDto billDto) {
        this.userDto = userDto;
        this.billDto = billDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public BillDto getBillDto() {
        return billDto;
    }

    @Override
    public String toString() {
        return "DiscountProcessorDto{" +
                "userDto=" + userDto +
                ", billDto=" + billDto +
                '}';
    }
}

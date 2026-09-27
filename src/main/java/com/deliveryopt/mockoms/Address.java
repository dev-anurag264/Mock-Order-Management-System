package com.deliveryopt.mockoms;

public record Address(
        String addressLine1,
        String area,
        String city,
        String state,
        String country,
        String pincode
) {
}

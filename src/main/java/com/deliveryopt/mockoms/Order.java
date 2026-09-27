package com.deliveryopt.mockoms;

public record Order(
        String orderId,
        String item,
        String status,
        String paymentStatus,
        Address address,
        String deliveryWindowStart, // "HH:mm", nullable - no window means any time in the shift
        String deliveryWindowEnd
) {
}

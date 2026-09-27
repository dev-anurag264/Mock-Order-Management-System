package com.deliveryopt.mockoms;

import java.util.List;

public record BatchOrderRequest(List<String> orderIds) {
}

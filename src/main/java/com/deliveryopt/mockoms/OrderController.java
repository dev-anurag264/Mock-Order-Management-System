package com.deliveryopt.mockoms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable String orderId) {
        return orderRepository.findById(orderId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("code", "ORDER_NOT_FOUND", "orderId", orderId)));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Order>> getOrdersBatch(@RequestBody BatchOrderRequest request) {
        List<Order> found = request.orderIds().stream()
                .map(orderRepository::findById)
                .flatMap(Optional::stream)
                .toList();
        return ResponseEntity.ok(found);
    }
}

package com.deliveryopt.mockoms;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Deterministic, fixed Bangalore test data. Do not randomize -
 * downstream optimization tests rely on stable order data.
 */
@Repository
public class OrderRepository {

    private final Map<String, Order> orders = new LinkedHashMap<>();

    public OrderRepository() {
        orders.put("ORD-101", new Order(
                "ORD-101", "Laptop", "READY_FOR_DELIVERY", "PREPAID",
                new Address("MG Road", "Indiranagar", "Bangalore", "Karnataka", "India", "560038"),
                "09:00", "11:00"
        ));
        orders.put("ORD-102", new Order(
                "ORD-102", "Headphones", "READY_FOR_DELIVERY", "COD",
                new Address("100 Feet Road", "Koramangala", "Bangalore", "Karnataka", "India", "560034"),
                "13:00", "15:00"
        ));
        orders.put("ORD-103", new Order(
                "ORD-103", "Mattress", "READY_FOR_DELIVERY", "PREPAID",
                new Address("Sarjapur Main Road", "HSR Layout", "Bangalore", "Karnataka", "India", "560102"),
                null, null // no window - deliverable any time in the shift
        ));
        orders.put("ORD-104", new Order(
                "ORD-104", "Office Chair", "READY_FOR_DELIVERY", "COD",
                new Address("Outer Ring Road", "Marathahalli", "Bangalore", "Karnataka", "India", "560037"),
                "10:00", "12:30"
        ));
        orders.put("ORD-105", new Order(
                "ORD-105", "Desk Lamp", "READY_FOR_DELIVERY", "PREPAID",
                new Address("Airport Road", "Hebbal", "Bangalore", "Karnataka", "India", "560024"),
                null, null
        ));
        // fixed non-happy-path cases for error-handling tests
        orders.put("ORD-201", new Order(
                "ORD-201", "Returned Item", "CANCELLED", "PREPAID",
                new Address("Church Street", "MG Road", "Bangalore", "Karnataka", "India", "560001"),
                null, null
        ));
        orders.put("ORD-202", new Order(
                "ORD-202", "Book", "READY_FOR_DELIVERY", "PREPAID",
                null, // missing address - triggers geocoding/validation failure downstream
                null, null
        ));
        // deliberately tight/early window to exercise time-window infeasibility
        orders.put("ORD-203", new Order(
                "ORD-203", "Urgent Medicine", "READY_FOR_DELIVERY", "PREPAID",
                new Address("Airport Road", "Hebbal", "Bangalore", "Karnataka", "India", "560024"),
                "09:00", "09:15"
        ));
    }

    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }
}

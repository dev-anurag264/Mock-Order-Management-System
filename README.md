# mock-oms

Deterministic mock Order Management System used for local development of `delivery-optimization`.

## Run

```bash
mvn spring-boot:run
```

Runs on `http://localhost:8081`.

## Endpoints

```http
GET  /api/orders/{orderId}
POST /api/orders/batch   { "orderIds": ["ORD-101", "ORD-102"] }
```

## Test data (fixed, do not expect it to change between runs)

| Order ID | Status              | Notes                  |
|----------|----------------------|-------------------------|
| ORD-101  | READY_FOR_DELIVERY   | Indiranagar             |
| ORD-102  | READY_FOR_DELIVERY   | Koramangala             |
| ORD-103  | READY_FOR_DELIVERY   | HSR Layout              |
| ORD-104  | READY_FOR_DELIVERY   | Marathahalli            |
| ORD-105  | READY_FOR_DELIVERY   | Hebbal                  |
| ORD-201  | CANCELLED            | for error-handling tests|
| ORD-202  | READY_FOR_DELIVERY   | missing address         |

Unknown order IDs return `404` with `{"code": "ORDER_NOT_FOUND", ...}`.

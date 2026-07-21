CREATE TABLE idempotency_keys
(
    id UUID PRIMARY KEY,
    idempotency_key VARCHAR(255) UNIQUE NOT NULL,
    order_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_idempotency_orders
        FOREIGN KEY(order_id)
            REFERENCES orders(id)
);
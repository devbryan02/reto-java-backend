CREATE TABLE order_history
(
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    status VARCHAR(30) NOT NULL,
    message VARCHAR(255),
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_history_orders
        FOREIGN KEY(order_id)
            REFERENCES orders(id)
);
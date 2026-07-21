CREATE TABLE order_items
(
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    product_name VARCHAR(150) NOT NULL,
    unit_price NUMERIC(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_order_items_orders
        FOREIGN KEY(order_id)
            REFERENCES orders(id)
);
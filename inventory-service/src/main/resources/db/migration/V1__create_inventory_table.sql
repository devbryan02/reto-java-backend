CREATE TABLE inventory
(
    product_id UUID PRIMARY KEY,
    available_stock INTEGER NOT NULL,
    reserved_stock INTEGER NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
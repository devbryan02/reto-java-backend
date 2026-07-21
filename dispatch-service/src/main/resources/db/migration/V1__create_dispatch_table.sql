CREATE TABLE dispatch
(
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL UNIQUE,
    tracking_number VARCHAR(100) UNIQUE NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
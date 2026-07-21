CREATE TABLE orders
(
    id UUID PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    status VARCHAR(30) NOT NULL,
    total_amount NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
CREATE TABLE saga_execution
(
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    status VARCHAR(30) NOT NULL,
    current_step VARCHAR(50) NOT NULL,
    failure_reason TEXT,
    started_at TIMESTAMP NOT NULL,
    finished_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
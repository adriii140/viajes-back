--Users
CREATE TABLE IF NOT EXISTS users(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

--Trips
CREATE TABLE IF NOT EXISTS trips(
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(255) NOT NULL DEFAULT 'PLANNING',
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    owner_id UUID NOT NULL REFERENCES users(id),
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
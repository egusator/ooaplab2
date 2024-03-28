CREATE TABLE user (
    id SERIAL PRIMARY KEY,
    username VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL
);

CREATE TABLE authority (
    id SERIAL PRIMARY KEY,
    authority_name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE user_authority (
    user_id INTEGER NOT NULL REFERENCES user(id),
    authority_id INTEGER NOT NULL REFERENCES authority(id)
);

CREATE TABLE otp (
    email VARCHAR PRIMARY KEY,
    otp_value INTEGER NOT NULL
);
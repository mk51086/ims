CREATE TABLE IF NOT EXISTS user_table
(
    user_id bigserial primary key,
    address character varying(255),
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255)
);
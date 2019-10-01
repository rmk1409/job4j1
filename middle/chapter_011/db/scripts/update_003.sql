CREATE TABLE IF NOT EXISTS users(
                    id serial primary key,
                    name character varying,
                    login character varying,
                    email character varying,
                    createdDate DATE
                    );
CREATE TABLE app_users (
                           id BIGSERIAL PRIMARY KEY,
                           username VARCHAR(255) NOT NULL UNIQUE,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           password VARCHAR(255) NOT NULL,
                           role VARCHAR(50) NOT NULL
);

CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL UNIQUE,
                            description VARCHAR(1000)
);

CREATE TABLE tickets (
                         id BIGSERIAL PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         description VARCHAR(2000),
                         status VARCHAR(50) NOT NULL,
                         priority VARCHAR(50) NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         user_id BIGINT NOT NULL,
                         category_id BIGINT NOT NULL,

                         CONSTRAINT fk_tickets_user
                             FOREIGN KEY (user_id)
                                 REFERENCES app_users(id),

                         CONSTRAINT fk_tickets_category
                             FOREIGN KEY (category_id)
                                 REFERENCES categories(id)
);
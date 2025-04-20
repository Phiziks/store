-- Create customer table
CREATE TABLE if not exists customer
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL
    );

-- Create order table
CREATE TABLE if not exists "order"
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    customer_id
    BIGINT
    NOT
    NULL,
    CONSTRAINT
    fk_customer
    FOREIGN
    KEY
(
    customer_id
) REFERENCES customer
(
    id
)
    );

CREATE TABLE if not exists product
(
    id
    BIGSERIAL,
    description
    varchar
(
    255
) NULL,
    CONSTRAINT product_pkey PRIMARY KEY
(
    id
)
    );

CREATE TABLE if not exists order_product
(
    id
    BIGSERIAL,
    order_id
    BIGINT
    NULL,
    product_id
    BIGINT
    NULL,
    CONSTRAINT
    order_product_pk
    PRIMARY
    KEY
(
    id
),
    CONSTRAINT order_product_order_fk FOREIGN KEY
(
    order_id
) REFERENCES "order"
(
    id
),
    CONSTRAINT order_product_product_fk FOREIGN KEY
(
    product_id
) REFERENCES product
(
    id
)
    );
CREATE TABLE subscription_option (
    pk_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    description VARCHAR(50) NOT NULL,
    cost DOUBLE NOT NULL,
    months_dur INTEGER NOT NULL,
    PRIMARY KEY(pk_id));

CREATE TABLE subscriber (
    pk_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    code VARCHAR(5) NOT NULL,
    fullname VARCHAR(50) NOT NULL,
    address VARCHAR(250),
    phone CHAR(10),
    start_date DATE,
    end_date DATE,
    subscription_option INTEGER NOT NULL,
    PRIMARY KEY(pk_id),
    FOREIGN KEY(subscription_option) REFERENCES subscription_option(pk_id));
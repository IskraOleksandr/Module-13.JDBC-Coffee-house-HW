CREATE TABLE IF NOT EXISTS public.CafeAssortment
(
    id SERIAL PRIMARY KEY,
    title_eng varchar(30),
    title_rus varchar(30),
    assortment_type varchar(10),
    price float
    )
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.clients
(
    id SERIAL PRIMARY KEY,
    full_name varchar(30),
    date_of_birth date,
    contact_phone varchar(15),
    contact_address varchar(30),
    discount float
    )

    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS public.staff
(
    id SERIAL PRIMARY KEY,
    full_name varchar(30),
    date_of_birth date,
    contact_phone varchar(15),
    contact_address varchar(30),
    position varchar(30)
    )
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.staff_work_schedule
(
    id SERIAL PRIMARY KEY,
    staff_id integer,
    day_of_week varchar(30),
    start_time time,
    end_time time,
    FOREIGN KEY (staff_id) REFERENCES staff (id) ON DELETE CASCADE
    )

    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.orders
(
    id SERIAL PRIMARY KEY,
    client_id integer,
    assortment_id integer,
    order_date date,
    order_time time,
    FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE,
    FOREIGN KEY (assortment_id) REFERENCES CafeAssortment (id) ON DELETE CASCADE
    )

    TABLESPACE pg_default;
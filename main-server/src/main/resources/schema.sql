DROP TABLE IF EXISTS public.USERS CASCADE;

CREATE TABLE IF NOT EXISTS public.USERS(ID BIGSERIAL PRIMARY KEY, USERNAME VARCHAR(500) not null unique, PASSWORD VARCHAR(500) not null, AUTHORITY VARCHAR(500) not null);

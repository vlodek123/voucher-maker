drop table if exists voucher;
drop table if exists voucher_seq;
drop table if exists capture;
drop table if exists capture_seq;
drop table if exists capture_item;
drop table if exists capture_item_seq;

create table voucher (
                     id bigint NOT NULL,
                       active boolean NOT NULL,
                       amount decimal(38,2) NOT NULL,
                       balance decimal(38,2) NOT NULL,
                       created_date timestamp(6),
                       expiration_date timestamp(6) NOT NULL,
                       last_updated_date timestamp(6),
                       voucher_code varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                       PRIMARY KEY (id),
                       CONSTRAINT uk_hvqsc8qffpt5okjmyot3a4b77 UNIQUE (voucher_code)

) engine=InnoDB;

create table voucher_seq (
    next_val bigint
) engine=InnoDB;

insert into voucher_seq values ( 1 );

create table capture (
                     id bigint NOT NULL,
                       created_date timestamp(6),
                       number_of_items integer NOT NULL,
                       processed boolean NOT NULL,
                       reason character varying(255) COLLATE utf8mb4_general_ci NOT NULL,
                       CONSTRAINT capture_pkey PRIMARY KEY (id)

) engine=InnoDB;

create table capture_seq (
    next_val bigint
) engine=InnoDB;

insert into capture_seq values ( 1 );

create table capture_item (
                      capture_amount numeric(38,2) NOT NULL,
                         processed boolean NOT NULL,
                         capture_id bigint,
                         id bigint NOT NULL,
                         voucher_id bigint,
                         voucher_code character varying(255) COLLATE utf8mb4_general_ci NOT NULL,
                         CONSTRAINT capture_item_pkey PRIMARY KEY (id),
                         CONSTRAINT fk64stmumr6n2oxraanytibawgx FOREIGN KEY (voucher_id)
                             REFERENCES voucher (id) MATCH SIMPLE
                             ON UPDATE NO ACTION
                             ON DELETE NO ACTION,
                         CONSTRAINT fkiiij7rsxuker0h441romktay2 FOREIGN KEY (capture_id)
                             REFERENCES capture (id) MATCH SIMPLE
                             ON UPDATE NO ACTION
                             ON DELETE NO ACTION
) engine=InnoDB;

create table capture_item_seq (
    next_val bigint
) engine=InnoDB;

insert into capture_item_seq values ( 1 );
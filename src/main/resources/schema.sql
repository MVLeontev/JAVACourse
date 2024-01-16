DROP TABLE IF EXISTS tpp_product_register cascade;
DROP TABLE IF EXISTS tpp_ref_account_type cascade;
DROP TABLE IF EXISTS tpp_ref_product_class cascade;
DROP TABLE IF EXISTS tpp_products cascade;
DROP TABLE IF EXISTS tpp_ref_product_register_type cascade;
DROP TABLE IF EXISTS account_pool cascade;

CREATE TABLE tpp_ref_account_type (
                                    internal_id serial  primary key ,
                                    value varchar(255)
                                 );


CREATE TABLE tpp_ref_product_class (
                                    internal_id serial primary key,
                                    value varchar(255),
                                    gbl_code varchar(255),
                                    gbl_name varchar(255),
                                    product_row_code varchar(255),
                                    product_row_name varchar(255),
                                    subclass_code varchar(255),
                                    subclass_name varchar(255)
                                 );


CREATE TABLE  tpp_ref_product_register_type (
                                    internal_id serial  primary key ,
                                    value varchar(255) ,
                                    register_type_name varchar(255) ,
                                    product_class_code bigint references tpp_ref_product_class(internal_id) ,
                                    account_type bigint references tpp_ref_account_type(internal_id)
                                 );

CREATE TABLE  tpp_product_register (
                                    id serial  primary key ,
                                    product_id bigint ,
                                    type bigint references tpp_ref_product_register_type(internal_id),
                                    account_id bigint ,
                                    currency_code varchar(255) ,
                                    state varchar(255),
                                    account_number varchar(25)
                                 );

CREATE TABLE tpp_products   (
                              id serial  primary key ,
                              agreement_id bigint,
                              product_code_id bigint,
                              client_id bigint,
                              type varchar(255),
                              number varchar(255),
                              priority bigint,
                              date_of_conclusion timestamp,
                              start_date_time timestamp,
                              end_date_time timestamp,
                              days integer,
                              penalty_rate numeric(10,4),
                              nso numeric(20,2),
                              threshold_amount numeric(20,2),
                              requisite_type varchar(255),
                              interest_rate_type varchar(10),
                              tax_rate numeric(10,4),
                              reason_close varchar(255),
                              state varchar(255)
                            );
CREATE TABLE account_pool (
                            branch_code varchar(25),
                            currency_code varchar(3),
                            mdm_code varchar(25),
                            priority_code varchar(25),
                            registry_type_code varchar(255),
                            accounts varchar(500) );


----------------- DATA -----------------
INSERT INTO tpp_ref_account_type (internal_id, value) VALUES
                                                        (1, 'Клиентский'),
                                                        (2, 'Внутрибанковский');

INSERT INTO tpp_ref_product_class
                        (internal_id, value, gbl_code, gbl_name, product_row_code, product_row_name, subclass_code, subclass_name)
                        VALUES
                        (1, '03.012.002', '03', 'Розничный бизнес', '012', 'Драг. металлы', '002', 'Хранение'),
                        (2, '02.001.005', '02', 'Розничный бизнес', '001', 'Сырье', '005', 'Продажа');

INSERT INTO  tpp_ref_product_register_type
                        (internal_id, value, register_type_name, product_class_code, account_type)
                        VALUES
                        (1, '03.012.002_47533_ComSoLd', 'Хранение ДМ.', 1, 1),
                        (2, '02.001.005_45343_CoDowFF', 'Серебро. Выкуп.',2, 1);


INSERT INTO account_pool
                    (branch_code, currency_code, mdm_code, priority_code, registry_type_code, accounts )
                    VALUES
                    ('0022', '800', '15', '00', '03.012.002_47533_ComSoLd','475335516415314841861,4753321651354151,4753352543276345'),
                    ('0021', '500', '13', '00', '02.001.005_45343_CoDowFF','453432352436453276,45343221651354151,4534352543276345');

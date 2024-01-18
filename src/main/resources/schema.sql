DROP TABLE IF EXISTS tpp_product_register cascade;
DROP TABLE IF EXISTS tpp_ref_account_type cascade;
DROP TABLE IF EXISTS tpp_ref_product_class cascade;
DROP TABLE IF EXISTS tpp_product cascade;
DROP TABLE IF EXISTS tpp_ref_product_register_type cascade;
DROP TABLE IF EXISTS account_pool cascade;

CREATE TABLE tpp_ref_account_type (
                                    internal_id serial  primary key ,
                                    valuess varchar(255) UNIQUE not null
                                 );

CREATE TABLE tpp_ref_product_class (
                                    internal_id serial primary key,
                                    valuess varchar(255) UNIQUE not null,
                                    gbl_code varchar(255),
                                    gbl_name varchar(255),
                                    product_row_code varchar(255),
                                    product_row_name varchar(255),
                                    subclass_code varchar(255),
                                    subclass_name varchar(255)
                                 );


CREATE TABLE tpp_ref_product_register_type (
                                    internal_id serial  primary key ,
                                    valuess varchar(255) UNIQUE not null,
                                    register_type_name varchar(255) ,
                                    product_class_code varchar(255) references tpp_ref_product_class(valuess) ,
                                    account_type varchar(255) references tpp_ref_account_type(valuess)
                                 );



CREATE TABLE tpp_product   (
                              id serial  primary key ,
                              agreement_id bigint,
                              product_code_id bigint references tpp_ref_product_class(internal_id) ,
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

CREATE TABLE  tpp_product_register (
                                    id serial  primary key ,
                                    product_id bigint references tpp_product(id) ,
                                    type varchar(255) references tpp_ref_product_register_type(valuess),
                                    account_id bigint ,
                                    currency_code varchar(255) ,
                                    state varchar(255),
                                    account_number varchar(25)
                                 );

CREATE TABLE account_pool (
                            id serial  primary key ,
                            branch_code varchar(25),
                            currency_code varchar(3),
                            mdm_code varchar(25),
                            priority_code varchar(25),
                            registry_type_code varchar(255),
                            accounts varchar(500) );



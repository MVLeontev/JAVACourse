DROP TABLE IF EXISTS tpp_product_register;

CREATE TABLE  tpp_product_register (
                                    id serial  primary key ,
                                    product_id bigint ,
                                    type varchar(255) ,
                                    account_id bigint ,
                                    currency_code varchar(255) ,
                                    state varchar(255)
                                 );
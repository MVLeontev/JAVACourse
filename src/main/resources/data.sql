----------------- DATA -----------------
INSERT INTO tpp_ref_account_type (internal_id, valuess) VALUES
                                                        (1, 'Клиентский'),
                                                        (2, 'Внутрибанковский');

INSERT INTO tpp_ref_product_class
                        (internal_id, valuess, gbl_code, gbl_name, product_row_code, product_row_name, subclass_code, subclass_name)
                        VALUES
                        (1, '03.012.002', '03', 'Розничный бизнес', '012', 'Драг. металлы', '002', 'Хранение'),
                        (2, '02.001.005', '02', 'Розничный бизнес', '001', 'Сырье', '005', 'Продажа');

INSERT INTO  tpp_ref_product_register_type
                        (internal_id, valuess, register_type_name, product_class_code, account_type)
                        VALUES
                        (1, '03.012.002_47533_ComSoLd', 'Хранение ДМ.', '03.012.002', 'Клиентский'),
                        (2, '02.001.005_45343_CoDowFF', 'Серебро. Выкуп.','02.001.005', 'Клиентский');


INSERT INTO account_pool
                    (branch_code, currency_code, mdm_code, priority_code, registry_type_code, accounts )
                    VALUES
                    ('0022', '800', '15', '00', '03.012.002_47533_ComSoLd','475335516415314841861,4753321651354151,4753352543276345'),
                    ('0021', '500', '13', '00', '02.001.005_45343_CoDowFF','453432352436453276,45343221651354151,4534352543276345');

INSERT INTO tpp_product
                    (id, product_code_id, client_id)
                    VALUES
                    (1, 1, 15);
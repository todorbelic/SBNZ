INSERT INTO app_user (username, role, password)
VALUES ('todor', 0,'$2a$10$c9ANL2IaV0RNMiwN0PKfSOqozojOQDXtxSDXjXggxpyyCfek9YmVG');
INSERT INTO app_user (username, role, password)
VALUES ('aleksa', 0 , '$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC');
INSERT INTO payment_card (cvc_number, expiration_date, card_number)
VALUES ('323', '2025-01-01', '123123123123');
INSERT INTO bank_account (account_number, money_balance, payment_card_id)
VALUES ('1', 5000, 1);
INSERT INTO package_account (type, client_id, bank_account_id)
VALUES (1, 2, 1);
INSERT INTO payment_card (cvc_number, expiration_date, card_number)
VALUES ('999', '2099-01-01', '999999999999');
INSERT INTO bank_account (account_number, money_balance, payment_card_id)
VALUES ('2', 0, 2);
INSERT INTO package_account (type, client_id, bank_account_id)
VALUES (1, 1, 2);
INSERT INTO transaction (amount, date, transaction_status, package_account_id, country)
VALUES (700,'2023-06-27 18:39:51.031890',0,1, 'BH');
INSERT INTO transaction (amount, date, transaction_status, package_account_id, country)
VALUES (7440,'2023-06-27 18:39:35.944626',1,1, 'RS');




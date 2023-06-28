INSERT INTO app_user (username, role, password, age)
VALUES ('todor', 0,'$2a$10$c9ANL2IaV0RNMiwN0PKfSOqozojOQDXtxSDXjXggxpyyCfek9YmVG', 32);
INSERT INTO app_user (username, role, password, age)
VALUES ('aleksa', 0 , '$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC', 65);
INSERT INTO app_user (username, role, password, age)
VALUES ('boban', 0 , '$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC', 27);
INSERT INTO app_user (username, role, password, age)
VALUES ('darko', 0 , '$2a$12$KYZWwLM7cbcVm5KZj.HwmOBq4SgX6GuslgQ8FAFbEqxPs2t0zpnwC', 27);
INSERT INTO app_user (username, role, password, age)
VALUES ('bankofficer', 1,'$2a$10$c9ANL2IaV0RNMiwN0PKfSOqozojOQDXtxSDXjXggxpyyCfek9YmVG', 32);
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


---STABILNA PRIMANJA----- #todor

INSERT INTO credit_request(client_id, total_amount, one_installment_amount, status, employment_info, num_of_installments, rbe_approval, installments_left)
VALUES (1, 10000, 100, 2, '{
  "employed": true,
  "indefinitely": true,
  "employmentStartDate": "2021-01-01",
  "employmentEndDate": "2023-06-30",
   "monthlyIncome": 2000.0
}', 12, true, 7);

INSERT INTO credit_request(client_id, total_amount, one_installment_amount, status, employment_info, num_of_installments, rbe_approval, installments_left)
VALUES (1, 10000, 200, 2, '{
  "employed": true,
  "indefinitely": true,
  "employmentStartDate": "2021-01-01",
  "employmentEndDate": "2023-06-30",
   "monthlyIncome": 2000.0
}', 12, true, 2);


---NEREDOVNO PLACANJE----

INSERT INTO credit_request(client_id, total_amount, one_installment_amount, status, employment_info, num_of_installments, rbe_approval, installments_left)
VALUES (3, 10000, 100, 2, '{
  "employed": true,
  "indefinitely": true,
  "employmentStartDate": "2021-01-01",
  "employmentEndDate": "2023-06-30",
   "monthlyIncome": 2000.0
}', 12, true, 7);

INSERT INTO credit_request(client_id, total_amount, one_installment_amount, status, employment_info, num_of_installments, rbe_approval, installments_left)
VALUES (3, 10000, 500, 2, '{
  "employed": true,
  "indefinitely": true,
  "employmentStartDate": "2021-01-01",
  "employmentEndDate": "2023-06-30",
   "monthlyIncome": 2000.0
}', 12, true, 2);

INSERT INTO credit_payment(client_id, amount, days_late, payment_date)
VALUES (3, 100, 18, '2021-01-01');

INSERT INTO credit_payment(client_id, amount, days_late, payment_date)
VALUES (3, 100, 18, '2021-02-01');




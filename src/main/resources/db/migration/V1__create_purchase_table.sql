CREATE TABLE public.tb_purchase (
                                purchase_id VARCHAR(50) NOT NULL,
                                description VARCHAR(50) NOT NULL,
                                transaction_date TIMESTAMP NOT NULL,
                                purchase_amount NUMERIC NOT NULL,
                                CONSTRAINT purchase_id PRIMARY KEY (purchase_id)
);
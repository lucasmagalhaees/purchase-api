CREATE TABLE public.purchases (
                                purchase_id VARCHAR(50) NOT NULL,
                                description VARCHAR(50) NOT NULL,
                                transaction_date DATE NOT NULL,
                                purchase_amount NUMERIC NOT NULL,
                                CONSTRAINT purchase_id PRIMARY KEY (purchase_id)
);
INSERT INTO public.tb_roles(
	role_id, authority)
	VALUES 
		(1, 'ADMIN'),
		(2, 'USER')
		
SELECT role_id, authority
	FROM public.tb_roles;
	
SELECT user_id, balance, password, username
	FROM public.tb_users;
	
UPDATE public.tb_users
	SET user_id=?, balance=?, password=?, username=?
	WHERE <condition>;
	
UPDATE public.tb_users
	SET balance = 0.0
	WHERE user_id = 1;
	
SELECT account_id, balance, name, user_id
	FROM public.tb_accounts;
	
UPDATE public.tb_accounts
	SET account_id=?, balance=?, name=?, user_id=?
	WHERE <condition>;
	
SELECT user_id, role_id
	FROM public.user_role_junction;
	
UPDATE public.user_role_junction
	SET user_id=1, role_id=1
	WHERE user_id=1;
	
INSERT INTO public.user_role_junction(
	user_id, role_id)
	VALUES (2, 1);
	
SELECT id, name
	FROM public.tb_categories;

INSERT INTO public.tb_categories(
	id, name)
	VALUES
		(1, 'Rent'),
		(2, 'Utilities'),
		(3, 'Groceries'),
		(4, 'Transportation'),
		(5, 'Healthcare'),
		(6, 'Entertainment'),
		(7, 'Food'),
		(8, 'Clothing'),
		(9, 'Debt Repayment'),
		(10, 'Other expense'),
		(11, 'Salary'),
		(12, 'Freelance Income'),
		(13, 'Investment Returns'),
		(14, 'Side Hustle'),
		(15, 'Rental Income'),
		(16, 'Gifts'),
		(17, 'Grants'),
		(18, 'Refunds'),
		(19, 'Reimbursements'),
		(20, 'Other Income');
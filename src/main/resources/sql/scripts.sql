INSERT INTO public.tb_roles(
	role_id, authority)
	VALUES 
		(1, 'ADMIN'),
		(2, 'USER')
		
SELECT role_id, authority
	FROM public.tb_roles;
	
SELECT user_id, balance, password, username
	FROM public.tb_users;
	
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
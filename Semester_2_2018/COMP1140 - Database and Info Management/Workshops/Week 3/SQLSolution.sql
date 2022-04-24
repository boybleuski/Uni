 
DROP TABLE ACCOUNT
  
CREATE TABLE ACCOUNT
(	accNo		CHAR(12)		Primary Key,
	accountType	VARCHAR(20)		NOT NULL,
	customer	VARCHAR(100)	NOT NULL,
	balance		DECIMAL(10,2)	CHECK (balance > 0)
)

SELECT	* FROM	ACCOUNT

INSERT INTO ACCOUNT VALUES ('6123-1234567', 'Award Saver', 'Peter Wang', 12234.94)
INSERT INTO ACCOUNT VALUES ('0231-2342142', 'Cheque', 'Mary Alison', 5342.98)
INSERT INTO ACCOUNT VALUES ('8232-3231134', 'NetSaver', 'Ally Kent', 23112.33)
INSERT INTO ACCOUNT VALUES ('6123-1234568', 'Cheque', 'Peter Wang', 231.31)


i.	Display customer’s names and balances for all accounts
SELECT	customer, balance
FROM	ACCOUNT

ii.	Display all account information for accounts with balance greater than AUD 5000.
SELECT	*
FROM	ACCOUNT
WHERE	balance > 5000

iii.	Display all account numbers of accounts of Mr. Peter Wang
SELECT	accNo
FROM	ACCOUNT
WHERE	customer = 'Peter Wang'

iv.	Display all information of accounts of type ‘Cheque’ with a balance over AUD 1000.
SELECT	*
FROM	ACCOUNT
WHERE	accountType = 'Cheque' AND balance > 1000


v. Display all account information customers ‘Ally Kent’ and ‘Peter Wang’
SELECT	*
FROM	ACCOUNT
WHERE	customer = 'Ally Kent' OR customer = 'Peter Wang'



d.	Add AUD 100 to account 0231-2342142 using UPDATE statement

UPDATE 	ACCOUNT
SET		balance = balance + 100
WHERE	accNo = '0231-2342142'

select * from account

e.	Delete  all accounts of Ally Kent

DELETE 	ACCOUNT
WHERE	customer = 'Ally Kent'

select * from account
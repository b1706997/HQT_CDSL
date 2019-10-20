create database ebanking;
use sqlproject;
create table role(
rolekey varchar(1),
roletype varchar(10), 
roledes varchar(30),
primary key(rolekey,roletype)
);

delimiter $$
	create procedure add_role(a varchar(1),b varchar(10), c varchar(30))
		begin
			insert into role(rolekey,roletype,roledes) values (a,b,c);
		end;
$$
	call add_role('U','user','A normal user');
	call add_role('E','employee','A working employee');
	call add_role('A','admin','Database admin');

create table person(
personid varchar(10) primary key,
fullname nvarchar(50),
birthday date,
rolekey varchar(1) check (rolekey='U' or rolekey='E' or rolekey='A'),
age tinyint,
joinday date,
phone1 numeric(11,0),
phone2 numeric(11,0),
addr1 nvarchar(4000),
addr2 nvarchar(4000),
email varchar (50),
zipcode varchar(10),
foreign key (rolekey) references role(rolekey) 
);
delimiter $$
	create procedure add_person(a varchar(10), b nvarchar(50), c date, d varchar(1), e tinyint, f date, phoneno numeric(11,0), address1 nvarchar(4000), mail varchar(50),zip varchar(10))
	begin
			insert into person(personid,fullname,birthday,rolekey,age,joinday,phone1,addr1,email,zipcode) values ( a,b,c,d,e,f,phoneno,address1,mail,zip);
	end;
$$

delimiter $$
	create procedure del_person(a varchar(10))
	begin
		delete from `person` where `personid` = a;
	end;
$$

create table acctype(
acckey varchar(1),
acctype varchar(10),
description nvarchar(4000),
rate decimal(4,2),
primary key (acckey,acctype)
);
-- ADD AN ACCOUNT TYPE
delimiter $$
	create procedure add_acctype(a varchar(1),b varchar(10),des nvarchar(4000),rate decimal(4,2))
	begin
		insert into acctype( acckey,acctype,description,rate) values (a,b,des,rate);
	end;
$$ 
-- DELETE ACCOUNT TYPE
delimiter $$
 create procedure del_acctype(b varchar(10))
 begin 
  delete from `acctype` where `accctype` = b;
end;
$$
create table account(
accountid varchar(10) primary key,
username varchar(50) unique key,
password varchar(50),
status bit,
acckey varchar(1), 
personid varchar(10),
foreign key (acckey) references acctype(acckey)
);
-- ADD ACCOUNT WITH A BALANCE
delimiter $$
create procedure add_account_balance( a varchar(10), u varchar(50), pass varchar(50), s bit, acckey varchar(1), personid varchar(10), balance decimal (17,4) )
 begin
insert into account(accountid , username, password, status, acckey, personid) values (a,u,pass,s,acckey,personid);
insert into balance(accountid, currrentbalance) values(a, balance);
end;
$$
-- ADD ACCOUNT
delimiter $$
create procedure add_account( a varchar(10), u varchar(50), pass varchar(50), s bit, acckey varchar(1), personid varchar(10) )
 begin
insert into account(accountid , username, password, status, acckey, personid) values (a,u,pass,s,acckey,personid);
end;
$$
-- DELETE ACCOUNT
delimiter $$
create procedure del_account(accid varchar(10))
begin
	delete from `account` where `accountid` = accid;
	delete from `balance` where `accountid` = accid;
end;
$$
-- CHANGE ACCOUNT STATUS 
delimiter $$
create procedure change_acc_status(accid varchar(10),sta bit)
begin
	update account 
	set  
		status = sta 
	where 
		accountid = accid;
end;
$$
-- CHANGE PASSWORD 
delimiter $$
create procedure change_acc_psw(accid varchar(10), psw varchar(50))
begin
	update account 
	set
		password = psw
	where 
		accountid = accid;
end;
$$
-- CHANGE ACCOUNT TYPE 
delimiter $$
create procedure change_acc_type(accid varchar(10), acckeyy varchar(1))
begin
	update account 
	set
		acckey = acckeyy
	where
		accountid = accid;
end;
$$
create table employee(
employid varchar(10) primary key,
accountid varchar(10) references account(accountid),
personid varchar(50) references person(personid),
workplace varchar(100),
payrate decimal(4,2)
);
create table balance(
accountid varchar(10) references account(accountid),
currentbalance decimal(17,4)
);
create table transactiontype(
transkey varchar(1),
transtype varchar(10),
description varchar(30),
fee decimal(17,4),
primary key (transkey,transtype)
);
create table transactionlog(
transactionid varchar(1),
senderid varchar(10) references account(accountid),
receiverid varchar(10) references account(accountid),
transkey varchar(1),
employid varchar(10) references employee(employid),
oldbalance decimal(17,4),
amount decimal(17,4),
newbalance decimal(17,4),
address nvarchar(4000),
time date
);













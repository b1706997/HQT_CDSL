create database ebanking;
use sqlproject;
create table role(
rolekey varchar(1),
roletype varchar(10), 
roledes varchar(30),
primary key(rolekey,roletype)
)CHARACTER SET utf8 COLLATE utf8_unicode_ci;

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
personid varchar(36) primary key,
fullname nvarchar(50) ,
birthday date,
rolekey varchar(1) check (rolekey='U' or rolekey='E' or rolekey='A'),
age tinyint,
joinday date,
phone1 varchar(11),
phone2 varchar(11),
addr1 nvarchar(4000) ,
addr2 nvarchar(4000) ,
email varchar (50),
zipcode varchar(6),
foreign key (rolekey) references role(rolekey) 
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
drop table person


delimiter $$
	create or replace procedure add_person( b nvarchar(50), c date, d varchar(1), e tinyint, f date, phoneno varchar(11), address1 nvarchar(4000), mail varchar(50),zip varchar(10))
	begin
			insert into person(personid,fullname,birthday,rolekey,age,joinday,phone1,addr1,email,zipcode) values ( uuid(),b,c,d,e,f,phoneno,address1,mail,zip);
	end;
$$
-- Database admin adding
call add_person('Nguyễn Nhất Sơn','1999-10-18','A',20,now(),0899008992,'Số 65 đường B3 phường Hưng Phú quận Cái Răng thành phố Cần Thơ','nguyennhatson1810@gmail.com','900000')

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
rate decimal(5,4),
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
-- CHANGE ACCOUNT RATE
delimiter $$
create procedure change_accrate(a varchar(10), b decimal (5,4))
begin
	update  acctype
	set rate = b 
	where acctype = a;
end;
$$
	call add_acctype('A','VIP','curBalance>10bils',0.08);
	call add_acctype('B','Platium','curBalance>1bil',0.075);
	call add_acctype('C','Gold','curBalance>500Mils',0.07);
	call add_acctype('D','Silver','curBalance>100Mils',0.065);
	call add_acctype('E','Normal','cur Balance>50k',0.06);

drop table account
create table account(
accountid varchar(36) primary key,
username varchar(50) unique key,
password varchar(50),
status bit,
acckey varchar(1), 
personid varchar(36) references person(personid) ,
foreign key (acckey) references acctype(acckey),
balance decimal(17,4) check (balance > 0)
);

-- ADD ACCOUNT WITH A BALANCE
delimiter $$
create procedure add_account_balance(  u varchar(50), pass varchar(50), s bit, acckey varchar(1), personid varchar(36), bal decimal (17,4) )
 begin
insert into account(accountid , username, password, status, acckey, personid,balance) values (uuid(),u,pass,s,acckey,personid,bal);
end;
$$
drop procedure add_account_balance
call add_account_balance('sonb1706997','siliconvalley',1,'E','a668c55b-f479-11e9-8dbd-107b44374ab8','50000');
call add_account_balance('son2','siliconvalley',1,'E','a668c55b-f479-11e9-8dbd-107b44374ab8','50000');
-- GET ACC ID 
delimiter $$ 
create or replace function get_accid(uname varchar(50))
returns varchar(36)
begin
	declare acccid varchar(36);
	select accountid into @acccid from account where username=uname;
	return(@acccid);
end;
$$
-- ADD ACCOUNT
delimiter $$
create or replace procedure add_account(  u varchar(50), pass varchar(50), s bit, acckey varchar(1), personid varchar(10) )
 begin
insert into account(accountid , username, password, status, acckey, personid) values (uuid(),u,pass,s,acckey,personid);
end;
$$
-- DELETE ACCOUNT
delimiter $$
create procedure del_account(accid varchar(36))
begin
	delete from `account` where `accountid` = accid;
	delete from `balance` where `accountid` = accid;
end;
$$
-- CHANGE ACCOUNT STATUS 
delimiter $$
create or replace procedure change_acc_status(accid varchar(36),sta bit)
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
create or replace procedure change_acc_psw(accid varchar(36), psw varchar(50))
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
create or replace procedure change_acc_type(accid varchar(36), acckeyy varchar(1))
begin
	update account 
	set
		acckey = acckeyy
	where
		accountid = accid;
end;
$$
create table employee(
employid varchar(36) primary key,
accountid varchar(36) references account(accountid),
personid varchar(36) references person(personid),
workplace varchar(100),
payrate decimal(4,2)
);

delimiter $$
create or replace procedure add_employ(a varchar(36),b varchar(36),c varchar(36),d varchar(100) , e decimal(4,2))
	begin
		insert into employee(employid,accountid,personid,workplace,payrate) values (a,b,c,d,e);
	end;
$$

delimiter $$
create or replace procedure del_employ(a varchar(36))
begin
	delete from `employee` where `employid` = a;
end;
$$

delimiter $$
create or replace procedure payrate_fix(a varchar(36), b decimal(4,2))
begin
	update employee
	set
		payrate = b
	where
		employid = a;
end;
$$

                                                                   
create table transactiontype(
transkey varchar(1),
transtype varchar(10),
description varchar(60),
fee decimal(17,4),
primary key (transkey,transtype)
);

delimiter $$ 
create procedure add_trans_type( a varchar(1), b varchar(10), c varchar(30),  d decimal(17,4))
begin
	insert into transactiontype(transkey,transtype,description,fee) values (a,b,c,d);
end;
$$

drop table transactiontype;
		call add_trans_type('D','deposit','User add their money',0);
		call add_trans_type('W','withdraw','User withdraw their money',0);
		call add_trans_type('S','send','User send their money',10000);
		call add_trans_type('R','receive','User receive money',0);
		call add_trans_type('P','salaryemployee','Pay employees their salary',0);
		call add_trans_type('I','interest','Pay users their monthly interest',0);
		call add_trans_type('F','fee','EBanking service fee',0);

create table transactionlog(
transactionid varchar(36) primary key,
senderid varchar(36) references account(accountid),
receiverid varchar(36) references account(accountid),
transkey varchar(1),
employid varchar(36) references employee(employid),
oldbalance decimal(17,4),
amount decimal(17,4),
newbalance decimal(17,4),
address nvarchar(4000),
time datetime
);
drop table transactionlog
delimiter $$
create or replace procedure add_trans_log(send varchar(36), receive varchar(36) , Tkey varchar(1), emp varchar(36) , old decimal (17,4), am decimal(17,4), neww decimal (17,4), addr varchar(4000),tim datetime)
begin
	insert into transactionlog( transactionid,senderid, receiverid, transkey, employid, oldbalance, amount, newbalance,address,time) values (uuid(),send,receive,Tkey,emp,old,am,neww,addr,tim);
end;
$$ 

-- ALL THE TRANSACTION 

--       DEPOSIT
delimiter $$
create procedure deposit(  accid varchar(36) ,  amountt decimal(17,4),inout result bit)
begin
	start transaction;
	-- CHECK ACCOUNT STATUS FIRST
	select status into @accstatus from account where accountid = accid;

	if @accstatus=1 
	then
		-- GET USER BALANCE
		select balance into @curbalance from account where accountid= accid;
		-- ADD MONEY
		set @newbalance = @curbalance + amountt;
		update account
		set balance = @newbalance
		where accountid = accid;
		-- CREATE TRANSACTION LOG
		call add_trans_log(accid,accid,'D',null,@curbalance,amountt,@newbalance,null,now());
		-- RETURN SUCCESS
		set result = 1;
	else
		-- RETURN FAIL
		set result = 0;
	end if;
	commit;
end;
$$
set @a=null;
call deposit(get_accid('sonb1706997'),70000,@a);
select @a;
-- WITHDRAW
delimiter $$
create or replace procedure withdraw(accid varchar(36), amountt decimal(17,4), inout result int )
withdraw:begin
-- RESULT:    1=success |  2=account locked | 3=insufficient balance
	start transaction;
		-- CHECK ACCOUNT STATUS 
		select status into @acccstatus from account where accountid = accid;
		select balance into @curbalance from account where accountid = accid;
		if @acccstatus=1
		then
			if amountt>=@curbalance
			then
				set result = 3;
				leave withdraw;
			else
				-- WITHDRAW THEIR MONEY
				set @newbalance = @curbalance - amountt;
				update account set balance = @newbalance where accountid = accid;
				-- CREATE TRANSACTION LOG
				call add_trans_log(accid,accid,'W',null,@curbalance,amountt,@newbalance,null,now());
				set result = 1;
			end if;
		else -- ACCOUNT IS LOCKED
			set result = 2;
		end if;
	commit;
end;
$$
-- TRANSFER 
delimiter $$
create or replace procedure transfer( send varchar(36), receive varchar(36), amountt decimal(17,4), inout result int )
transfer:begin -- RESULT : 1=success    2=Accounts locked     3=insufficient balance
	start transaction;
		-- CHECK ACCOUNT STATUS
		select status into @accsend from account where accountid = send;
		select status into @accreceive from account where accountid = receive;
		if @accsend=1 and @accreceive=1
		then
		-- CHECK SENDER ACCOUNT BALANCE
			select balance into @sendbalance from account where accountid = send;
			select balance into @receivebalance from account where accountid = receive;
			set @checkk = @sendbalance + @receivebalance; -- CHECK CONSISTENCY
			if amountt>=@sendbalance
			then
				set result = 3;
				leave transfer;
			else -- START TRANSFERING
				set @newbalance1 = @sendbalance - amountt;
				set @newbalance2 = @receivebalance + amountt;
				update account set balance = @newbalance1 where accountid = send;
				update account set balance = @newbalance2 where accountid = receive;
				call add_trans_log(send,receive,'S',null,@sendbalance,amountt,@newbalance1,null,now());
				call add_trans_log(receive,send,'R',null,@receivebalance,amountt,@newbalance2,null,now());
				set result = 1;
			end if;
		else
			set result = 2;
			leave transfer;
		end if;
	select balance into @checkA from account where accountid = send;
	select balance into @checkB from account where accountid = receive;
	if @checkk = @checkA + @checkB
	then
		commit;
	else
		rollback;
	end if;
end;
$$
	set @a = null;
	call transfer(get_accid('sonb1706997'),get_accid('son2'),100000,@a);
	select @a;

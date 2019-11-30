use sqlproject;
call update_acctype(get_accid('sonb1706997'));
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
identity varchar(12) unique key
foreign key (rolekey) references role(rolekey) 
) CHARACTER SET utf8 COLLATE utf8_unicode_ci;
drop table person
alter table person add identity varchar(12)
-- GET PERSONID

select check_exist('1');
select check_exist('a');

delimiter $$
create or replace function get_perid(iden varchar(12))
returns varchar(36)
begin
	declare perid varchar(36);
	select personid into @perid from person where identity =iden;
	return(@perid);
end;
$$
select * from person;
select get_perid('092099004996');
select personid from person where identity = '092099004996'
delimiter $$
	create or replace procedure add_person( b nvarchar(50),iden varchar(12), c date, d varchar(1), phoneno varchar(11), address1 nvarchar(4000), mail varchar(50),zip varchar(10))
	begin
			declare agee tinyint;
			set agee = year(now()) - year(c);
			insert into person(personid,fullname,birthday,rolekey,age,joinday,phone1,addr1,email,zipcode,identity) values ( uuid(),b,c,d,agee,now(),phoneno,address1,mail,zip,iden);
	end;
$$
-- Database admin adding
call add_person('Nguyễn Nhất Sơn','092099003291','1999-10-18','A',0899008992,'Số 65 đường B3 phường Hưng Phú quận Cái Răng thành phố Cần Thơ','nguyennhatson1810@gmail.com','900000')
call add_person('Nguyen Van A','123456789125','2000-10-10','U',0561253333,'65 Wall Street','test@gmail.com','900000');
select * from person;
delimiter $$
create or replace procedure del_person(perid varchar(36),out result bit)
exitplace:begin
		declare accid varchar(36);
		declare done int default false;
		declare cur1 cursor for select accountid from account where personid=perid;
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
-- CAN ONLY REMOVE USER
	select rolekey into @key1 from person where personid = perid;
	if @key1 = 'A' or @key1 = 'E'
	then
		set result = 0;
		leave exitplace;
	else
		-- a user => remove logs => remove account => remove person
		open cur1;
		del_loop: LOOP
			if done then leave del_loop; 
			end if;
			fetch cur1 into accid;
			delete from transactionlog where senderid=accid or receiverid = accid or employid = accid;
		end LOOP;
		set result = 1;
	end if;
	close cur1;
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
-- UPDATE ACCOUNT TYPE
delimiter $$
create procedure update_acctype(accid varchar(36))
begin
	select balance into @curbalance from account where accountid = accid;
	set @akey = null;
	-- ACCTYPE CONDITION
	if @curbalance >= 10000000000 -- 10bils
		then set @akey = 'A';
	elseif @curbalance >= 1000000000 -- 1bil
		then set @akey = 'B';
	elseif @curbalance >= 500000000 -- 500mils
		then set @akey = 'C'; 
	elseif @curbalance >= 100000000 -- 100mils
		then set @akey ='D';
	else 
		  set @akey ='E';
	end if;
	update account set acckey = @akey where accountid = accid;
end ;
$$


	call add_acctype('A','VIP','curBalance>10bils',0.08);
	call add_acctype('B','Platium','curBalance>1bil',0.075);
	call add_acctype('C','Gold','curBalance>500Mils',0.07);
	call add_acctype('D','Silver','curBalance>100Mils',0.065);
	call add_acctype('E','Normal','cur Balance>50k',0.06);

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
create or replace procedure add_account_balance(  u varchar(50), pass varchar(50), personid varchar(36), bal decimal (17,4) )
 begin
	insert into account(accountid , username, password, status, personid,balance) values (uuid(),u,pass,1,personid,0);
	set @abc = null;
	call deposit(get_accid(u),bal,@abc);
end;
$$

call add_account_balance('sonb1706997','siliconvalley','a668c55b-f479-11e9-8dbd-107b44374ab8','50000');
call add_account_balance('son2','siliconvalley','a668c55b-f479-11e9-8dbd-107b44374ab8','50000');
call add_account_balance('testAcc','123',get_perid('092099004996'),600000000);
call add_account_balance('testacc4','123',get_perid('092099004996'),600000000);

-- CHECK IF USERNAME EXIST 
delimiter $$
create or replace function check_exist( usr varchar(50))
returns smallint
begin
	declare result smallint ;
	set result = 0;	
	select accountid into @accid from account where username = usr;
	select username into @unamecheck from account where accountid=@accid;
	if @accid = null or @unamecheck !=usr
	then
		set result = 0;
	else
		set result = 15;
	end if;
	return result;
end;
$$

select check_exist('son2');
select check_exist('son2');


-- RETURN INTEREST RATE
delimiter $$
create or replace function get_accrate(accid varchar(36))
returns decimal(5,4)
begin
	select  acckey into @key from account where accountid = accid;
	select rate into @ra from acctype where acckey = @key;
	return @ra;
end;
$$

-- GET USERNAME 
delimiter $$
create or replace function get_uname(accid varchar(36))
returns varchar(50)
begin
	select username into @uname from account where accountid = accid;
	return @uname;
end;
$$

-- RETURN BALANCE
delimiter $$
create or replace function get_balance(accid varchar(36))
returns decimal(17,4)
begin
	declare bal decimal(17,4) ;
	if accid is null
		then
			return null;
		end if;
	select balance into @bal from account where accountid = accid;
	return @bal;
end;
$$
-- GET ACCTYPE 
delimiter $$
create or replace function get_acctype(accid varchar(36))
returns varchar(10)
begin
	if accid is null
		then
			return null;
	end if;
	select acckey into @akey from account where accountid = accid;
	select acctype into @atype from acctype where acckey = @akey;
	return @atype;
end;
$$
-- GET PERID FROM ACCID
delimiter $$
create or replace function get_perid_accid(accid varchar(36))
returns varchar(36)
begin
	select personid into @perid from person where accountid = accid;
	return @perid;
end;
$$
-- GET OWNER NAME BY ACCID
delimiter $$
create or replace function get_name(accid varchar(36))
returns varchar(50)
begin
	if accid is null
		then
			return null;
	end if;
	select personid into @perid from account where accountid = accid;
	select fullname into @fname from person where personid = @perid;
	return @fname;
end;
$$
-- GET ACC ID 
delimiter $$ 
create or replace function get_accid(uname varchar(50))
returns varchar(36)
begin
	declare acccid varchar(36);
	select accountid into @acccid from account where username=uname;
	-- CHECK IF CORRECT
	select username into @unamecheck from account where accountid = @acccid;
	if @acccid is null or @unamecheck !=uname
	then 
		set @acccid = null;
	end if;
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
personid varchar(36) references person(personid),
workplace varchar(100),
payrate decimal(4,2)
);
-- FIRE EMPLOYEE
delimiter $$
create or replace procedure fire_emp(empid varchar(36))
begin
	select personid into @perid from employee where employid = empid;
	delete from employee where employid = empid;
	update person set rolekey='U' where personid=@perid;
end;
$$
-- GET EMPLOYID
delimiter$$
create or replace function get_employid(perid varchar(36) )
returns varchar(36)
begin
	declare eid varchar(36);
	select employid into eid from employee where personid = perid;
	return eid;
	end;
$$
select get_employid (get_perid('092099004996'))
delimiter $$
create or replace procedure add_employ(b varchar(36),d varchar(100) , e decimal(4,2))
	begin
		insert into employee(employid,personid,workplace,payrate) values (uuid(),b,d,e);
	end;
$$

select * from person;
select * from account where personid='609b6c85-10c9-11ea-911f-107b44374ab8';

call add_employ(get_accid('sonb1706997'),get_perid('092099004996'),'So 65 B3',1.6);
delimiter $$
create or replace procedure pay(eid varchar(36) )
begin
start transaction;
	-- GET ACCOUNTID AND PAYRATE;
	select payrate  into @prate from employee where employid = eid;
    select accountid into @accid from employee where employid = eid;
	-- CHECK IF REALLY AN EMPLOYEE
	select personid into @perid from account where accountid = @accid;
	select balance into @b from account where accountid = @accid ;
	set @am = 1390000*@prate;
	set  @newbalance = @b + @am;
	update account set balance = @newbalance where accountid = @accid ;
	call add_trans_log(@accid, @accid, 'P' , @accid,@b,@am,@newbalance,null,now() );
commit;
end;
$$
call pay(get_employid(get_perid('092099004996')));
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

delimiter $$
create procedure change_trans_fee(a varchar(1),  fees decimal(17,4))
begin
	update transactiontype set fee = fees where transkey = a; 
end;
$$

drop table transactiontype;
		call add_trans_type('D','deposit','User add their money',0);
		call add_trans_type('W','withdraw','User withdraw their money',10000);
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
-- APPLICATION PROCEDURES, VIEWS AND FUNCTIONS

-- ACCOUNT TYPE VIEWS
select count(*) from (account acb,select acckey from acctype as abc) where abc.acckey = acb.acckey


-- TRANSACTION LOGS VIEW
delimiter $$
create or replace view usertranslogview  as
	select tl.senderid as senderid,tl.receiverid as receiverid,tl.time as time,t.transtype as transactiontype,tl.amount,tl.oldbalance as oldbalance, tl.newbalance as newbalance,tl.address
	from
		transactionlog tl,
		transactiontype t
	where
		tl.transkey=t.transkey
	order by time desc;
$$

select * from usertranslogview

delimiter $$
create or replace view usertransfer  as
	select v.senderid,v.time,v.transactiontype, v.amount, get_uname(v.receiverid) as username , v.oldbalance,v.newbalance 
	from usertranslogview v 
	where  transactiontype = 'send' or transactiontype = 'receive'
	order by time desc;
$$

use sqlproject
delimiter $$
create or replace view userlogs as 
select senderid,time, transactiontype,  if(senderid=receiverid,null,account.username) as receiverid,amount,oldbalance,newbalance from usertranslogview,account where account.accountid=receiverid
order by time desc;
$$

select * from usertransfer

-- LOGIN
delimiter $$
create or replace procedure login(uname varchar(50),psw varchar(50),out result bit, out rtype varchar(10),out accid varchar(36))
begin
	if get_accid(uname) is null -- check username exists
	then
		set result = 0;
	else
		select password into @psw1 from account where accountid=get_accid(uname);
		if psw=@psw1 -- check password correct
		then
			-- get this person role in the database
			select personid into @perid from account where accountid = get_accid(uname);
			select rolekey into @rkey from person where personid = @perid;
			select roletype into @rtype1 from role where rolekey = @rkey;
			set result = 1;
			set rtype = @rtype1;
			set accid = get_accid(uname);
		else
			set result = 0;
		end if;
	end if;
end;
$$
--       DEPOSIT
delimiter $$
create or replace procedure deposit(  accid varchar(36) ,  amountt decimal(17,4),inout result bit)
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
call deposit(get_accid('sonb1706997'),3000000000,@a);
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
select * from account;
	set @a = null;
	CALL withdraw(get_accid('sonb1706997'),400000000,@a);
	call transfer(get_accid('sonb1706997'),get_accid('son2'),100000,@a);
	select @a;

-- INTEREST PAY
delimiter$$
create procedure interest(accid varchar(36))
begin
start transaction;
	-- GET USER BALANCE
	select balance into @curbalance from account where accountid= accid;
	-- ADD INTEREST
	select acckey into @keyy from account where accountid = accid;
	select rate into @rate from acctype where acckey = @keyy;
	
	set @am = @curbalance * @rate;
	set @newbalance = @curbalance + @am;
	update account set balance = @newbalance where accountid = accid;
	call add_trans_log(accid,accid,'I',null,@curbalance,@am,@newbalance,null,now());
	commit;
end;
$$

-- ROLLBACK A TRANSACTION










delimiter $$
create trigger updateacckey before insert on transactionlog
for each row
begin
	if NEW.senderid != NEW.receiverid
	then -- WHICH MEAN A TRANSFER
		call update_acctype(NEW.senderid);
		call update_acctype(NEW.receiverid);
	else -- WHICH MEAN A DEPOSIT/WITHDRAW/FEE OR SALARY PAID
		call update_acctype(NEW.senderid);
	end if;
end;
$$

select * from usertransfer

create view test 
as
select * from transactionlog order by time

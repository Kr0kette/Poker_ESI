

drop table Player;
drop table GameHistory;
drop table Sequences;


create table Player (
	id integer primary key,
	name text not null unique,
	money integer default 0,
	lastConnection text
	);


create table GameHistory(
        idGame integer not null,
        namePlayer text not null,
        gain integer,
        handCategory text,
        constraint npl_FK foreign key(namePlayer) references Player(name),
        constraint gm_PK primary key (idGame,namePlayer)
        );


create table SEQUENCES (
     id varchar(50) not null,
     sValue numeric(10) not null,
     constraint IDSEQUENCE primary key (id));

Insert Into SEQUENCES Values('Player', 0);
Insert into SEQUENCES Values('GameHistory',1);


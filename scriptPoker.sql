

drop table Player;
drop table Review;
drop table GameHistory;
drop table Sequences;



create table Player (
	id integer primary key,
	name text not null unique,
	money integer default 0,
	lastConnection text
	);


create table Review (
        id integer not null,
        idGame integer not null,
        namePlayer text not null,
        rating numeric(2) not null,
        details text,
        constraint pk_Review primary key(id),
        constraint fk_FromPlayer foreign key(namePlayer) references Player(name),
        constraint unq_Gm_Pl unique (idGame,namePlayer)

        );


create table GameHistory(
        id integer not null,
        idGame integer not null,
        namePlayer text not null,
        gain integer,
        handCategory text,
        constraint npl_FK foreign key(namePlayer) references Player(name),
        constraint gm_PK primary key (id),
        constraint unq unique (idGame,namePlayer)
        );


create table SEQUENCES (
     id varchar(50) not null,
     sValue numeric(10) not null,
     constraint IDSEQUENCE primary key (id));

Insert Into SEQUENCES Values('Player', 0);
Insert into SEQUENCES Values('GameHistory',1);
Insert into SEQUENCES Values('GameId',2);
Insert into SEQUENCES values('Review',3);



CREATE table registracija(
clan_id int primary key auto_increment not null,
ime varchar(30) not null,
prezime varchar(50) not null,
brojMob varchar(20) not null unique,
lozinka varchar(70) not null
);


select * from registracija;

SELECT * from registracija WHERE brojMob=0912016999;



CREATE table clanOOOP(
clan_id int primary key auto_increment not null,
ime varchar(30) not null,
prezime varchar(50) not null,
broj_mob varchar(20) not null
);

CREATE table knjigaOOOP(
knjiga_id int primary key auto_increment not null,
naslov varchar(100) not null,
autor varchar(100) not null,
god_izdanja int(4) not null
);

select * from clanOOOP;
select * from knjigaOOOP;

#autor i knjiga bi trebali imati agregaciju jer nije zadovoljena normalna forma 1NF i 3NF
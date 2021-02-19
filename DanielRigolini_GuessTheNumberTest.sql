drop database if exists bullsandcowsTest;

create database bullsandcowsTest;

use bullsandcowsTest; 

create table Game (
	Game_ID Int primary key auto_increment NOT NULL,
    Answer char(4),
    Game_Status BOOLEAN default false
);

create table Round (
	Round_ID Int primary key auto_increment NOT NULL,
    Game_ID Int NOT NULL,
    Guess char(4),
    Round_Time TIMESTAMP default current_timestamp,
    Result char(7),
    foreign key fk_Round_Game_ID (Game_ID)
		references Game(Game_ID)
);

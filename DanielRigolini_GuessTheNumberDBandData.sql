drop database if exists bullsandcows;

create database bullsandcows;

use bullsandcows; 

create table Game (
	Game_ID Int primary key auto_increment NOT NULL,
    Answer char(4),
    Is_Finished BOOLEAN default false
);
Insert INTO Game(Game_ID, Answer, Is_Finished) 
Values(1, "8901", true),
(2, "3728", true),
(3, "6429", true),
(4, "2130", true);

create table Round (
	Round_ID Int primary key auto_increment NOT NULL,
    Game_ID Int NOT NULL,
    Guess char(4),
    Round_Time TIMESTAMP default CURRENT_TIMESTAMP,
    Result char(7),
    foreign key fk_Round_Game_ID (Game_ID)
		references Game(Game_ID)
);

Insert INTO Round(Round_ID, Game_ID, Guess, Round_Time, Result) 
Values(1, 1, "2415", "2021-01-25 10:32:15", "e:0:p:1"),
(2, 1, "9854", "2021-01-25 11:56:35", "e:0:p:2"),
(3, 1, "7601", "2021-02-25 13:01:24", "e:2:p:0"),
(4, 1, "8901", "2021-02-25 15:21:34", "e:4:p:0"),
(5, 2, "2873", "2021-04-11 01:33:40", "e:0:p:4"),
(6, 2, "3728", "2021-04-12 06:13:08", "e:4:p:0"),
(7, 3, "6429", "2021-09-27 14:24:12", "e:4:p:0"),
(8, 4, "2130", "2021-11-20 19:45:23", "e:4:p:0");

CREATE TABLE Boss
(
companyId int NOT NULL,
companyName char(20) ,
BossId INT ,
PRIMARY KEY (companyId),
FOREIGN KEY (BossId) REFERENCES Boss(BossId)
)
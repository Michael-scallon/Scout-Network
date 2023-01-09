# Creating the schema
CREATE SCHEMA <continent>;

# Activating the schema
USE <continent>;

# Creating the table
CREATE table player(
    Name VARCHAR(45) PRIMARY KEY,
    Age INT NOT NULL,
    Position VARCHAR(45) NOT NULL,
    Region VARCHAR(45) NOT NULL,
    Minutes INT NOT NULL,
    Nationality VARCHAR(45) NOT NULL,
    Appearances INT NOT NULL,
    Goals INT NOT NULL,
    Assists INT NOT NULL,
    Conceded INT NOT NULL,
    Assist_per FLOAT NOT NULL,
    Goals_per FLOAT NOT NULL,
    Conceded_per FLOAT NOT NULL
);

# Inserting the data into the table - must turn on local_infile in both workbench and the server
LOAD DATA LOCAL INFILE '/path/to/data_<continent>.csv'
INTO TABLE player
FIELDS TERMINATED BY ',';

# Updating Conceded_per column
UPDATE player SET player.Conceded_per = ROUND(player.Conceded/(player.Minutes/90), 2) WHERE player.Minutes > 0;
UPDATE player SET player.Name = REPLACE(player.Name, ' ', '_');
UPDATE player SET player.Region = REPLACE(player.Region, ' ', '_');
UPDATE player SET player.Nationality = REPLACE(player.Nationality, ' ', '_');

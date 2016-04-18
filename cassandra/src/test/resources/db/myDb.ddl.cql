CREATE TABLE test1.t2 (
    voornaam text,
    achternaam text,
    gebjaar int,
    aantal int,
    PRIMARY KEY ((voornaam, achternaam), gebjaar)
);
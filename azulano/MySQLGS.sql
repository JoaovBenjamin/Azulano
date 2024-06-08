create database azulano;
use azulano;

CREATE TABLE T_AZL_SPECIES (
    id_species NUMERIC primary key,
    nm_cientifico VARCHAR(255) NOT NULL,
    nm_comum VARCHAR(255),
    tx_descricao VARCHAR(255)
);

CREATE TABLE T_AZL_HABITATS (
    id_habitat NUMERIC PRIMARY KEY,
    nm_habitat VARCHAR(255) NOT NULL,
    tx_descricao VARCHAR(255)
);

CREATE TABLE T_AZL_LOCATIONS (
    id_location NUMERIC PRIMARY KEY,
    ds_latitude VARCHAR(50) NOT NULL,
    ds_longitude VARCHAR(50) NOT NULL
);

select * from T_AZL_SPECIES;
select * from T_AZL_HABITATS;
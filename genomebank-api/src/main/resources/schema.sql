CREATE TABLE species (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    scientificName VARCHAR(150) NOT NULL,
    commonName VARCHAR(150)
);

CREATE TABLE genome (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    version VARCHAR(100) NOT NULL,
    species_id BIGINT NOT NULL,
    FOREIGN KEY (species_id) REFERENCES species(id)
);

CREATE TABLE chromosome (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    length BIGINT NOT NULL,
    sequence LONGTEXT,
    genome_id BIGINT NOT NULL,
    FOREIGN KEY (genome_id) REFERENCES genome(id)
);

CREATE TABLE gene (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    symbol VARCHAR(100) NOT NULL,
    startPosition BIGINT NOT NULL,
    endPosition BIGINT NOT NULL,
    strand VARCHAR(5),
    sequence LONGTEXT,
    chromosome_id BIGINT NOT NULL,
    FOREIGN KEY (chromosome_id) REFERENCES chromosome(id)
);



CREATE TABLE function (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    descriptiveName VARCHAR(150),
    category ENUM('BP', 'MF', 'CC') NOT NULL
);


CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') NOT NULL
);

CREATE TABLE gene_function (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gene_id BIGINT NOT NULL,
    function_id BIGINT NOT NULL,
    evidence VARCHAR(100),
    FOREIGN KEY (gene_id) REFERENCES gene(id),
    FOREIGN KEY (function_id) REFERENCES function(id)
);
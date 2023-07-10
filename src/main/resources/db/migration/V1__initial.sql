CREATE TABLE IF NOT EXISTS `PRICES` (
                                        `ID`         INTEGER  PRIMARY KEY AUTO_INCREMENT,
                                        `PRIORITY` INTEGER NOT NULL,
                                        `PRICE` DOUBLE NOT NULL,
                                        `CURR` VARCHAR(20) NOT NULL,
                                        `BRAND_ID` INTEGER  NOT NULL,
                                        `PRODUCT_ID` INTEGER  NOT NULL,
                                        `PRICE_LIST` INTEGER  NOT NULL,
                                        `START_DATE` TIMESTAMP  NOT NULL,
                                        `END_DATE` TIMESTAMP  NOT NULL
);

CREATE TABLE IF NOT EXISTS `BRANDS` (
                                        `ID`         INTEGER  PRIMARY KEY AUTO_INCREMENT,
                                        `CODE` VARCHAR(20) NOT NULL,
                                        `NAME` VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS `PRODUCTS` (
                                        `ID`         INTEGER  PRIMARY KEY AUTO_INCREMENT,
                                        `CODE` VARCHAR(20) NOT NULL,
                                        `NAME` VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS `PRICES_LIST` (
                                        `ID`         INTEGER  PRIMARY KEY AUTO_INCREMENT,
                                        `PRICE` DOUBLE NOT NULL
    );
ALTER TABLE PRICES
    ADD CONSTRAINT BRAND_ID_FK
        FOREIGN KEY (BRAND_ID) REFERENCES BRANDS;

ALTER TABLE PRICES
    ADD CONSTRAINT PRODUCT_ID_FK
        FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS;

ALTER TABLE PRICES
    ADD CONSTRAINT PRICE_LIST_ID_FK
        FOREIGN KEY (PRICE_LIST) REFERENCES PRICES_LIST;

-- Commit the transaction
COMMIT;
CREATE TABLE Performers (
  id         BIGINT       NOT NULL,
  firstName  VARCHAR(25)  NOT NULL,
  lastName   VARCHAR(25)  NOT NULL,
  age        INT          NOT NULL,
  PRIMARY KEY (id)
)

  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE utf8_bin;

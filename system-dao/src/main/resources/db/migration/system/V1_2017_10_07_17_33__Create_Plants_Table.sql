CREATE TABLE Plants (
  id         BIGINT       NOT NULL,
  name       VARCHAR(50)  NOT NULL,
  sowingArea DOUBLE       NOT NULL,
  maintainer VARCHAR(128) NOT NULL,
  created    BIGINT       NOT NULL,
  modified   BIGINT       NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE utf8_bin;

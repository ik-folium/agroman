package by.agro.config.db;

import java.util.Properties;

public class DefaultMySqlDataSourceProperties extends Properties {

    public DefaultMySqlDataSourceProperties(String username, String password, String url) {

        super();

        this.setProperty("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

        this.setProperty("dataSource.user", username);
        this.setProperty("dataSource.password", password);
        this.setProperty("dataSource.url", url);

        /*
         * socketTimeout
         * connectTimeout
         * If the DB server silently drops the connection for any reason during any phase, we want the client to be
         * able to close the connection and retry.
         */
        this.setProperty("dataSource.socketTimeout", "10000");
        this.setProperty("dataSource.connectTimeout", "10000");

        /*
         * prepStmtCacheSize
         * This sets the number of prepared statements that the MySQL driver will cache per connection. The default
         * is a conservative 25. We recommend setting this to between 250-500.
         */
        this.setProperty("dataSource.prepStmtCacheSize", "250");

        /*
         * prepStmtCacheSqlLimit
         * This is the maximum length of a prepared SQL statement that the driver will cache. The MySQL default is
         * 256. In our experience, especially with ORM frameworks like Hibernate, this default is well below the
         * threshold of generated statement lengths. Our recommended setting is 2048.
         */
        this.setProperty("dataSource.prepStmtCacheSqlLimit", "2048");

        /*
         * cachePrepStmts
         * Neither of the above parameters have any effect if the cache is in fact disabled, as it is by default. You
         * must set this parameter to true.
         */
        this.setProperty("dataSource.cachePrepStmts", "true");

        this.setProperty("dataSource.rewriteBatchedStatements", "true");
    }
}

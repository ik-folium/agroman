package by.agro.config.db;

import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

public class DefaultMySqlDataSourceHikariConfig extends HikariConfig {

    public DefaultMySqlDataSourceHikariConfig(Properties properties, Integer minIdle, Integer maxPoolSize) {

        super(properties);

        /*
          ? maximumPoolSize
          This property controls the maximum size that the pool is allowed to reach, including both idle and in-use
          connections. Basically this value will determine the maximum number of actual connections to the database
          backend. A reasonable value for this is best determined by your execution environment. When the pool
          reaches this size, and no idle connections are available, calls to getConnection() will block for up to
          connectionTimeout milliseconds before timing out.
          Default: 10
         */
        this.setMaximumPoolSize(maxPoolSize);

        /*
          ⌚ connectionTimeout
          This property controls the maximum number of milliseconds that a client (that's you) will wait for a
          connection from the pool. If this time is exceeded without a connection becoming available, a SQLException
          will be thrown. 1000ms is the minimum value.
          Default: 30000 (30 seconds)
         */
        long connectionTimeoutMs = 10000;
        this.setConnectionTimeout(connectionTimeoutMs);

        /*
         * ⌚ validationTimeout
         * This property controls the maximum amount of time that a connection will be tested for aliveness. This
         * value must be less than the connectionTimeout. The lowest accepted validation timeout is 1000ms (1 second).
         * Default: 5000
         */
        long validationTimeoutMs = 3000;
        this.setValidationTimeout(validationTimeoutMs);

        /*
         * ⌚ maxLifetime
         * This property controls the maximum lifetime of a connection in the pool. When a connection reaches this
         * timeout it will be retired from the pool, subject to a maximum variation of +30 seconds. An in-use
         * connection will never be retired, only when it is closed will it then be removed. We strongly recommend
         * setting this value, and it should be at least 30 seconds less than any database-level connection timeout. A
         * value of 0 indicates no maximum lifetime (infinite lifetime), subject of course to the idleTimeout setting.
         * Default: 1800000 (30 minutes)
         */
        long maxLifetimeMs = 1800000;
        this.setMaxLifetime(maxLifetimeMs);

        /*
         * ? minimumIdle
         * This property controls the minimum number of idle connections that HikariCP tries to maintain in the pool.
         * If the idle connections dip below this value, HikariCP will make a best effort to add additional
         * connections quickly and efficiently. However, for maximum performance and responsiveness to spike demands,
         * we recommend not setting this value and instead allowing HikariCP to act as a fixed size connection pool.
         * Default: same as maximumPoolSize
         */
        this.setMinimumIdle(minIdle);

        /*
         * ⌚idleTimeout
         * This property controls the maximum amount of time that a connection is allowed to sit idle in the pool.
         * Whether a connection is retired as idle or not is subject to a maximum variation of +30 seconds, and average
         * variation of +15 seconds. A connection will never be retired as idle before this timeout. A value of 0 means
         * that idle connections are never removed from the pool. Default: 600000 (10 minutes)
         */
        long idleTimeoutMs = 60000;
        this.setIdleTimeout(idleTimeoutMs);

        /*
         * ⌚leakDetectionThreshold
         * This property controls the amount of time that a connection can be out of the pool before a message is
         * logged indicating a possible connection leak. A value of 0 means leak detection is disabled. Lowest
         * acceptable value for enabling leak detection is 2000 (2 secs). Default: 0
         */
        long leakDetectionThresholdMs = 0;
        this.setLeakDetectionThreshold(leakDetectionThresholdMs);
    }
}

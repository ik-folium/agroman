package by.agro.plant

import by.agro.config.db.DefaultMySqlDataSourceHikariConfig
import by.agro.config.db.DefaultMySqlDataSourceProperties
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import groovy.sql.Sql
import org.flywaydb.core.Flyway
import spock.lang.Shared
import spock.lang.Specification

class SpringJdbcPlantPlantingDaoTest extends Specification {

    @Shared
    SpringJdbcPlantPlantingDao dao

    @Shared
    Sql sql

    void setupSpec() {

        def dataSourceProperties = new DefaultMySqlDataSourceProperties(
            "root",
            "password",
            "jdbc:mysql://localhost:33060/test"
        )
        HikariConfig dataSourceConfig = new DefaultMySqlDataSourceHikariConfig(
            dataSourceProperties, 1, 50
        )
        def dataSource = new HikariDataSource(dataSourceConfig)

        sql = new Sql(dataSource)
        sql.call("drop database if exists `test`")
        sql.call("create database if not exists `test`")

        def flyway = new Flyway()
        flyway.setDataSource(dataSource)
        flyway.setLocations("classpath:db/migration/system")
        flyway.migrate()

        dao = new SpringJdbcPlantPlantingDao(dataSource)
    }

    def "should save plant"() {

        given: 'plant to save'
            PlantPlanting plant = PlantPlanting.builder()
                .id(1)
                .name('potato')
                .sowingArea(10.0)
                .maintainer('Alex')
                .build()
        when: 'saving plant with dao'
            dao.save(plant)
        then: 'db contains saved plant'
            def savedPlant = sql.rows('SELECT * FROM Plants WHERE id = 1')[0]
            savedPlant.id == 1
            savedPlant.name == 'potato'
            savedPlant.sowingArea == 10.0
            savedPlant.maintainer == 'Alex'
            savedPlant.created > 0
            savedPlant.modified > 0
    }
}

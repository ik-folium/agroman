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

    def "should update plant"() {

        given: 'plant to update'
            sql.execute('INSERT INTO Plants VALUES (2, "potato", 5.5, "John", 1, 1)')
            def updatingPlant = new PlantPlanting()
            updatingPlant.id = 2
            updatingPlant.sowingArea = 12.5
            updatingPlant.maintainer = 'SomeOne'
            updatingPlant.modified = 100
        when: 'updating plant with dao'
            dao.update(updatingPlant)
        then: 'db contains updated plant'
            def updatedPlant = sql.rows('SELECT * FROM Plants WHERE id = 2')[0]
            updatedPlant.id == 2
            updatedPlant.sowingArea == 12.5
            updatedPlant.maintainer == 'SomeOne'
            updatedPlant.modified > 0
    }

    def "should delete plant"() {

        given: 'plant to delete'
            sql.execute('INSERT INTO Plants VALUES (3, "cucumber", 5.0, "Mom", 1, 1)')

        def deletingPlant = new PlantPlanting()
        deletingPlant.id = 3

        when: 'deleting plant with dao'
            dao.delete(deletingPlant.id)
        then: 'db contains no deleted plant'
            def deletedPlant = sql.rows('SELECT * FROM Plants WHERE id =3')[0]
            deletedPlant == null

    }

    def "should return plant by id"() {

        given: 'plant to return by id'
            sql.execute('INSERT INTO Plants VALUES (2, "potato", 5.5, "John", 1, 1)')
            def validPlant = new PlantPlanting()
            validPlant.id = 2
        when: 'returning plant with dao'
            dao.get(validPlant.id)
        then: 'displaying selected plant'
            def displayedPlant = sql.rows('SELECT * FROM Plants WHERE id = 2')[0]
            displayedPlant.id == 2
    }

    def "should return all plants"() {

        given: 'plants to return'
            sql.execute('INSERT INTO Plants VALUES (1, "tomato", 3.5, "Alexander", 1, 1)')
            sql.execute('INSERT INTO Plants VALUES (2, "potato", 5.5, "John", 1, 1)')

        when: 'returning plants with dao'
            dao.findAll()
        then: 'displaying all plants'
            def displayedPlants = sql.rows('SELECT * FROM Plants')
            displayedPlants != null
            displayedPlants.size() == 2
            displayedPlants[0].name == 'tomato'
            displayedPlants[1].name == 'potato'
    }
}

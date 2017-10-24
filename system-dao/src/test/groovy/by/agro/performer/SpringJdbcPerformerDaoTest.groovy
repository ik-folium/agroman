package by.agro.performer

import by.agro.config.db.DefaultMySqlDataSourceHikariConfig
import by.agro.config.db.DefaultMySqlDataSourceProperties
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import groovy.sql.Sql
import org.flywaydb.core.Flyway
import spock.lang.Shared
import spock.lang.Specification

class SpringJdbcPerformerDaoTest extends Specification {

    @Shared
    SpringJdbcPerformerDao dao

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

        dao = new SpringJdbcPerformerDao(dataSource)
    }

    def "should save performer"() {

        given: 'performer to save'
            Performer perf = Performer.builder()
                    .id(1)
                    .firstName('Kostya')
                    .lastName('Hard')
                    .age(38)
                    .build()
        when: 'saving performer with dao'
             dao.save(perf)
        then: 'db contains saved performer'
            def savedPlant = sql.rows('SELECT * FROM Performers WHERE id = 1')[0]
            savedPlant.id == 1
            savedPlant.firstName == 'Kostya'
            savedPlant.lastName == 'Hard'
            savedPlant.age == 38
    }

    def "should update performer"() {

        given: 'performer to update'
            sql.execute('INSERT INTO Performers VALUES (2, "John", "Old", 40)')
            def updatingPerformer = new Performer()
            updatingPerformer.id = 2
            updatingPerformer.firstName = 'Jack'
            updatingPerformer.lastName = 'New'
            updatingPerformer.age = 45
        when: 'updating performer with dao'
            dao.update(updatingPerformer)
        then: 'db contains updated performer'
            def updatedPerformer = sql.rows('SELECT * FROM Performers WHERE id = 2')[0]
            updatedPerformer.id == 2
            updatedPerformer.firstName == 'Jack'
            updatedPerformer.lastName == 'New'
            updatedPerformer.age == 45
    }

    def "should delete performer"() {

        given: 'performer to delete'
            sql.execute('INSERT INTO Performers VALUES (3, "Pumpkin", "Head", 16)')

            def deletingPerformer = new Performer()
            deletingPerformer.id = 3
        when: 'deleting performer with dao'
            dao.delete(deletingPerformer.id)
        then: 'db contains no deleted performer'
            def deletedPerformer = sql.rows('SELECT * FROM Performers WHERE id =3')[0]
            deletedPerformer == null

    }

    def "should return performer by id"() {

        given: 'performer to return by id'
            sql.execute('INSERT INTO Performers VALUES (2, "Usama", "BenLaden", 59)')
            def validPerformer = new Performer()
            validPerformer.id = 2
        when: 'returning performer with dao'
            dao.get(validPerformer.id)
        then: 'displaying selected performer'
            def displayedPerformer = sql.rows('SELECT * FROM Performers WHERE id = 2')[0]
            displayedPerformer.id == 2
    }

    def "should return all performers"() {

        given: 'performers to return'
            sql.execute('INSERT INTO Performers VALUES (1, "Mr", "Brown", 49)')
            sql.execute('INSERT INTO Performers VALUES (2, "Ms", "White", 45)')

        when: 'returning performers with dao'
            dao.findAll()
        then: 'displaying all performers'
            def displayedPlants = sql.rows('SELECT * FROM Performers')
            displayedPlants != null
            displayedPlants.size() == 2
            displayedPlants[0].lastName == 'Brown'
            displayedPlants[1].lastName == 'White'
    }

}
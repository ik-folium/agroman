package by.agro.performer

import by.agro.plant.Performer
import spock.lang.Specification

class SimplePerformerServiceTest extends Specification {

    PerformerDao dao = Mock()

    SimplePerformerService performersTest = new SimplePerformerService(dao)

    def "should delegate saving to dao"() {

        given:
            def addPerformerDto = new AddPerformerDto(1, 'Kostya', 'Hard', 38)
        when:
            performersTest.addPerformer(addPerformerDto)
        then:
            1 * dao.save({ Performer performer ->
                performer.id == 1 && performer.firstName == 'Kostya' && performer.lastName == 'Hard' && performer.age == 38
            })
    }

    def "should delegate updating to dao"() {

        given:
            def updatePerformerDto = new UpdatePerformerDto(1, 'Kostya', 'Hard', 38)
        when:
            performersTest.updatePerformer(updatePerformerDto)
        then:
            1 * dao.update({ Performer performer ->
                performer.id == 1 && performer.firstName == 'Kostya' && performer.lastName == 'Hard' && performer.age == 38
            }) >> new Performer(1, 'Kostya', 'Hard', 38)
    }

    def "should return from dao by id"() {

        given:
            def validGetPerformerDto = new GetPerformerDto(1)
        when:
            def foundPerformer = performersTest.getPerformer(validGetPerformerDto)
        then: 'white box'
            1 * dao.get({ long id -> id == validGetPerformerDto.id }) >> new Performer(validGetPerformerDto.id, 'Kostya', 'Hard', 38)
        then: 'black box'
            foundPerformer.id == validGetPerformerDto.id
            foundPerformer.firstName == 'Kostya'
    }

    def "should delete from dao by id"() {

        given:
            def deletePerformerDto = new DeletePerformerDto(1)
        when:
            performersTest.deletePerformer(deletePerformerDto)
        then:
            1 * dao.delete({ long id -> id == deletePerformerDto.id })
    }

    def "should return from dao all performers"() {

        when:
            List<Performer> foundPerformers = performersTest.findAllPerformers()
        then:
            1 * dao.findAll() >> [new Performer(firstName: 'Zoya'), new Performer(firstName: 'Kostya')]
        then:
            foundPerformers != null
            foundPerformers.size() == 2
            foundPerformers[0].firstName == 'Zoya'
            foundPerformers[1].firstName == 'Kostya'
    }
}

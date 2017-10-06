package by.agro.plant

import spock.lang.Specification

class SimplePlantPlantingServiceTest extends Specification {

    PlantPlantingDao dao = Mock()

    SimplePlantPlantingService serviceUnderTest = new SimplePlantPlantingService(dao)

    def "should delegate saving to dao"() {

        given:
            def addPlantingPlantDto = new AddPlantingPlantDto(1, 'potato', 10.5, 'Vasya Pupkin')
        when:
            serviceUnderTest.addPlantingPlant(addPlantingPlantDto)
        then:
            1 * dao.save({ PlantPlanting plant ->
                plant.id == 1 && plant.name == 'potato' && plant.sowingArea == 10.5 && plant.maintainer == 'Vasya Pupkin'
            })
    }

    def "should delegate updating to dao"() {

        given:
            def updatePlantingPlantDto = new UpdatePlantingPlantInfoDto(1, 'potato', 10.5, 'Vasya Pupkin')
        when:
            def result = serviceUnderTest.updatePlantingPlant(updatePlantingPlantDto)
        then:
            1 * dao.update({ PlantPlanting plant ->
                plant.id == 1 && plant.name == 'potato' && plant.sowingArea == 10.5 && plant.maintainer == 'Vasya Pupkin'
            }) >> new PlantPlanting(1, 'potato', 10.5, 'Vasya Pupkin')
            /* then:
                result.id == 1
                */
    }

    def "should return from dao by id"() {

        given:
            def validGetPlantingPlantDto = new GetPlantingPlantDto(1)
        when:
            def foundPlantingPlant = serviceUnderTest.getPlantingPlant(validGetPlantingPlantDto)
        then: 'white box'
            1 * dao.get({ long id -> id == validGetPlantingPlantDto.id }) >> new PlantPlanting(validGetPlantingPlantDto.id, 'potato', 10.5, 'Vasya Pupkin')
        then: 'black box'
            foundPlantingPlant.id == validGetPlantingPlantDto.id
            foundPlantingPlant.name == 'potato'
    }

    def "should delete from dao by id"() {

        given:
            def deletePlantingPlantDto = new DeletePlantingPlantDto(1)
        when:
            serviceUnderTest.deletePlantingPlant(deletePlantingPlantDto)
        then:
            1 * dao.delete({ long id -> id == deletePlantingPlantDto.id })
    }

    def "should return from dao all plants info"() {

        when:
            List<PlantPlanting> foundPlants = serviceUnderTest.findAllPlantingPlants()
        then:
            1 * dao.findAll() >> [new PlantPlanting(name: 'tomato'), new PlantPlanting(name: 'potato')]
        then:
            foundPlants != null
            foundPlants.size() == 2
            foundPlants[0].name == 'tomato'
            foundPlants[1].name == 'potato'
    }
}

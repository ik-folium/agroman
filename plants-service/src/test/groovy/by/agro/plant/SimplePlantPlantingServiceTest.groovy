package by.agro.plant

import spock.lang.Specification

class SimplePlantPlantingServiceTest extends Specification {

    PlantPlantingDao dao = Mock()

    SimplePlantPlantingService service = new SimplePlantPlantingService(dao)

    def "should delegate saving to dao"() {

        given:
            def addPlantingPlantDto = new AddPlantingPlantDto('potato', 10.5, 'Vasya Pupkin')
        when:
            service.addPlantingPlant(addPlantingPlantDto)
        then:
            1 * dao.save({ PlantPlanting plant ->
                plant.name == 'potato' && plant.sowingArea == 10.5 && plant.maintainer == 'Vasya Pupkin'
            })
    }

    def "should delegate updating to dao"() {

        given:
            def updatePlantingPlantDto = new UpdatePlantingPlantInfoDto(1, 'potato', 10.5, 'Vasya Pupkin')
        when:
            service.updatePlantingPlant(updatePlantingPlantDto)
        then:
            1 * dao.update({ PlantPlanting plant ->
                plant.id == 1 && plant.name == 'potato' && plant.sowingArea == 10.5 && plant.maintainer == 'Vasya Pupkin'
            })
    }
}

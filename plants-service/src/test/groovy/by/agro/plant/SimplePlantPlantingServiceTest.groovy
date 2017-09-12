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
}

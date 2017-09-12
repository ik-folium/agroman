package by.agro.plant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimplePlantPlantingService {

    private PlantPlantingDao plantPlantingDao;

    public void addPlantingPlant(AddPlantingPlantDto addPlantingPlantDto) {

        PlantPlanting planting = new PlantPlanting();
        planting.setName(addPlantingPlantDto.getName());
        planting.setSowingArea(addPlantingPlantDto.getSowingArea());
        planting.setMaintainer(addPlantingPlantDto.getMaintainer());

        plantPlantingDao.save(planting);
    }
}

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

    public void updatePlantingPlant(UpdatePlantingPlantInfoDto updatePlantingPlantInfoDto) {

        PlantPlanting planting = new PlantPlanting();
        
        planting.setId(updatePlantingPlantInfoDto.getId());
        planting.setName(updatePlantingPlantInfoDto.getName());
        planting.setSowingArea(updatePlantingPlantInfoDto.getSowingArea());
        planting.setMaintainer(updatePlantingPlantInfoDto.getMaintainer());

        plantPlantingDao.update(planting);
    }
}

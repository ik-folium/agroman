package by.agro.performer;

import by.agro.plant.Performer;

import java.util.List;

public interface PerformerService {

    void addPerformer(AddPerformerDto addPerformerDto);

    void updatePerformer(UpdatePerformerDto updatePerformerDto);

    Performer getPerformer(GetPerformerDto getPerformerDto);

    void deletePerformer(DeletePerformerDto deletePerformerDto);

    List<Performer> findAllPerformers();

}

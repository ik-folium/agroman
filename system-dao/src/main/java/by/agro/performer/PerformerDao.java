package by.agro.performer;

import java.util.List;

public interface PerformerDao {

    void save(Performer performer);

    void update(Performer performer);

    Performer get(long id);

    void delete(long id);

    List<Performer> findAll();
}

package poo.repository;
import poo.model.Investigador;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface InvestigadorRepository {
	List<Investigador> findAll();

    Optional<Investigador> findById(String id);

    void add(Investigador investigador);

    void removeById(String id);

    void loadFromFile() throws IOException;

    void saveToFile() throws IOException;

}

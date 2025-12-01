package poo.repository;

import poo.model.Temporada;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TemporadaRepository {
	List<Temporada> findAll();

    Optional<Temporada> findById(String id);

    void add(Temporada temporada);

    void removeById(String id);

    void loadFromFile() throws IOException;

    void saveToFile() throws IOException;

}

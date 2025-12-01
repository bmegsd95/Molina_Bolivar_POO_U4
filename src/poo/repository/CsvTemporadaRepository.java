package poo.repository;
import poo.model.Temporada;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class CsvTemporadaRepository implements TemporadaRepository {
	private final Path filePath;
    private final List<Temporada> temporadas = new ArrayList<>();

    public CsvTemporadaRepository(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Temporada> findAll() {
        return new ArrayList<>(temporadas);
    }

    @Override
    public Optional<Temporada> findById(String id) {
        return temporadas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public void add(Temporada temporada) {
        removeById(temporada.getId());
        temporadas.add(temporada);
    }

    @Override
    public void removeById(String id) {
        temporadas.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public void loadFromFile() throws IOException {
        temporadas.clear();

        if (Files.notExists(filePath)) {
            return;
        }

        var lineas = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        for (String linea : lineas) {
            if (linea.isBlank()) {
                continue;
            }
            String[] partes = linea.split(";");
            if (partes.length < 4) {
                continue;
            }
            String id = partes[0];
            String idSerie = partes[1];
            int numero = Integer.parseInt(partes[2]);
            int cantEpisodios = Integer.parseInt(partes[3]);

            temporadas.add(new Temporada(id, idSerie, numero, cantEpisodios));
        }
    }

    @Override
    public void saveToFile() throws IOException {
        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            for (Temporada temporada : temporadas) {
                writer.write(temporada.toCsv());
                writer.newLine();
            }
        }
    }
	

}

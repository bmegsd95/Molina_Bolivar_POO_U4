package poo.repository;
import org.junit.jupiter.api.*;
import poo.model.Pelicula;
import poo.model.SerieTV;
import poo.model.Documental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CsvContenidoRepositoryTest {
	private Path tempFile;
    private CsvContenidoRepository repo;

    @BeforeEach
    void setUp() throws IOException {
        // Creamos un archivo temporal para pruebas
        tempFile = Files.createTempFile("contenidos_test", ".csv");
        repo = new CsvContenidoRepository(tempFile);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void guardarYCargarPeliculas() throws IOException {
        Pelicula peli = new Pelicula(
                "P001", "Matrix", 1999, 136,
                "Sci-Fi", 9.5, "Wachowski"
        );

        repo.add(peli);
        repo.saveToFile();

        // Repo nuevo para verificar que sí lee del archivo
        CsvContenidoRepository repo2 = new CsvContenidoRepository(tempFile);
        repo2.loadFromFile();

        assertEquals(1, repo2.findAll().size());
        assertEquals("Matrix", repo2.findAll().get(0).getTitulo());
    }

    @Test
    void guardarYCargarDiferentesTipos() throws IOException {
        repo.add(new Pelicula("P001", "Matrix", 1999, 136, "Sci-Fi", 9.5, "Wachowski"));
        repo.add(new SerieTV("S001", "Breaking Bad", 2008, 47, "Drama", 9.8, 5));
        repo.add(new Documental("D001", "Planeta Tierra", 2006, 60, "Naturaleza", 9.2, "Vida salvaje"));

        repo.saveToFile();
        repo.loadFromFile();

        assertEquals(3, repo.findAll().size());
    }

    @Test
    void reemplazaContenidoConMismoID() {
        repo.add(new Pelicula("P001", "Matrix", 1999, 136, "Sci-Fi", 9.5, "Wachowski"));
        repo.add(new Pelicula("P001", "Matrix Reloaded", 2003, 138, "Sci-Fi", 7.2, "Wachowski"));

        assertEquals(1, repo.findAll().size());
        assertEquals("Matrix Reloaded", repo.findAll().get(0).getTitulo());
    }

}

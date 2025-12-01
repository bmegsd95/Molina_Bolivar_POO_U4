package poo.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poo.model.Actor;
import poo.model.Pelicula;
import poo.repository.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

 class SistemaContenidoControllerTest {
	private SistemaContenidoController controller;

    @BeforeEach
    void setUp() throws IOException {
        // Archivos temporales
        Path c = Files.createTempFile("contenidos", ".csv");
        Path a = Files.createTempFile("actores", ".csv");
        Path t = Files.createTempFile("temporadas", ".csv");
        Path i = Files.createTempFile("investigadores", ".csv");

        controller = new SistemaContenidoController(
                new CsvContenidoRepository(c),
                new CsvActorRepository(a),
                new CsvTemporadaRepository(t),
                new CsvInvestigadorRepository(i)
        );
    }

    @Test
    void crearPeliculaFunciona() {
        controller.crearPelicula(
                "P001", "Matrix", 1999, 136,
                "Sci-Fi", 9.5, "Wachowski"
        );

        assertEquals(1, controller.obtenerTodosLosContenidos().size());
    }

    @Test
    void crearActorFunciona() {
        controller.crearActor("A001", "Keanu Reeves", "Canadiense");

        assertEquals(1, controller.obtenerActores().size());
        assertEquals("Keanu Reeves", controller.obtenerActores().get(0).getNombre());
    }

}

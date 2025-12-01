package poo.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaTest {
	@Test
    void creaPeliculaConDatosCorrectos() {
        Pelicula peli = new Pelicula(
                "P001", "Matrix", 1999, 136,
                "Sci-Fi", 9.5, "Wachowski"
        );

        assertEquals("P001", peli.getId());
        assertEquals("Matrix", peli.getTitulo());
        assertEquals(1999, peli.getAnioEstreno());
        assertEquals(136, peli.getDuracionMinutos());
        assertEquals("Sci-Fi", peli.getGenero());
        assertEquals(9.5, peli.getCalificacion());
        assertEquals("Wachowski", peli.getDirector());
    }

    @Test
    void toCsvTieneFormatoEsperado() {
        Pelicula peli = new Pelicula(
                "P001", "Matrix", 1999, 136,
                "Sci-Fi", 9.5, "Wachowski"
        );

        String csv = peli.toCsv();

        // PELICULA;id;titulo;anio;duracion;genero;calificacion;director
        assertTrue(csv.startsWith("PELICULA;"));
        assertTrue(csv.contains("Matrix"));
        assertTrue(csv.endsWith("Wachowski"));
    }

    @Test
    void noPermiteIdVacio() {
        // El constructor de ContenidoAudiovisual lanza IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new Pelicula(
                        "   ", "Sin ID", 2000, 100,
                        "Drama", 5.0, "Alguien"
                )
        );
    }

}

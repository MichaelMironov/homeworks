package api.com.rickandmortyapi;

import api.com.rickandmortyapi.pojo.characters.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;

import static api.com.rickandmortyapi.Steps.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MortyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MortyTest.class);

    @BeforeAll
    public static void prepare() throws IOException {

        System.getProperties().load(ClassLoader.getSystemResourceAsStream("test.properties"));
        String baseUri = System.getProperty("mortyUrl");
        if (baseUri == null || baseUri.isEmpty()) {
            throw new RuntimeException("В файле \"test.properties\" отсутствует значение \"mortyUrl\"");
        }
        baseURI = baseUri;
        Specification.installSpecification(Specification.requestSpec(baseURI));

    }

    @Test
    void characterComparingTest() {

        Person morty = getCharacterByName("Morty Smith");

        String lastEpisodeMorty = morty.getEpisode().stream().reduce((first, second) -> second).get();

        List<String> characters = getCharactersOfEpisode(getId(lastEpisodeMorty));

        int idLastEpisodeCharacter = getId(characters.get(characters.size() - 1));

        Person jerry = getCharacterById(idLastEpisodeCharacter);

        LOGGER.info("Раса {}: {}. Раса {}: {}.", morty.getName(), morty.getSpecies(), jerry.getName(), jerry.getSpecies());

        LOGGER.info("Местонахождение {}: {}. Местонахождение {}: {}.", morty.getName(), morty.getLocation(), jerry.getName(), jerry.getLocation());

        assertEquals(morty.getSpecies(), jerry.getSpecies());

        assertNotEquals(morty.getLocation(), jerry.getLocation());

    }

}

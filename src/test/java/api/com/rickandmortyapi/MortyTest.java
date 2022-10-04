package api.test;

import api.com.rickandmortyapi.pojo.characters.Person;
import api.com.rickandmortyapi.spec.Specification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static api.com.rickandmortyapi.steps.Steps.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MortyTest {

    private static int EPISODE_ID;
    private static final Logger LOGGER = LoggerFactory.getLogger(MortyTest.class);

    @BeforeAll
    public static void prepare() throws IOException {

        System.getProperties().load(ClassLoader.getSystemResourceAsStream("test.properties"));
        String  baseUri = System.getProperty("mortyUrl");
        if (baseUri == null || baseUri.isEmpty()) {
            throw new RuntimeException("В файле \"test.properties\" отсутствует значение \"base.uri\"");
        }
        baseURI = baseUri;
        Specification.installSpecification(Specification.requestSpec(baseURI));

    }

    @Test
    void characterLastEpisodeTest() throws IOException {

        JSONObject morty = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/morty.json"))));

        List<String> mortyEpisodes = getCharacterEpisodes(morty);

        String lastEpisodeMorty = mortyEpisodes.get(mortyEpisodes.size()-1);

        List<String> characters = getCharactersOfEpisode(getId(lastEpisodeMorty));

        int lastCharId = getId(characters.get(characters.size()-1));

        Person person = getCharacterById(lastCharId);

        String mortyLocation = (String) morty.getJSONObject("location").get("name");
        String mortySpecies = (String) morty.get("species");

        LOGGER.info("Раса {}: {}. Раса {}: {}.",morty.get("name"), mortySpecies, person.getName(), person.getSpecies());

        LOGGER.info("Локация {}: {}. Локация {}: {}.",morty.get("name"), mortyLocation, person.getName(), person.getLocation());

        assertEquals(mortySpecies, person.getSpecies());

        assertNotEquals(mortyLocation, person.getLocation());

    }

}

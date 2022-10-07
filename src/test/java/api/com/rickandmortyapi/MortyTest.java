package api.com.rickandmortyapi;

import api.com.rickandmortyapi.pojo.characters.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static api.Specification.installSpecification;
import static api.Specification.requestSpec;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static utils.configurations.Configuration.getConfigurationValue;

public class MortyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MortyTest.class);

    @BeforeAll
    public static void prepare() {

        installSpecification(requestSpec(getConfigurationValue("mortyUrl")));

    }

    @Test
    void characterComparingTest() {

        Person morty = Steps.getCharacterByName("Morty Smith");

        LOGGER.info(morty.toString());

        String lastEpisodeMorty = morty.getEpisode().stream().reduce((first, second) -> second).get();

        LOGGER.info("Последний эпизод Морти: {}", lastEpisodeMorty);

        List<String> characters = Steps.getCharactersOfEpisode(Steps.getId(lastEpisodeMorty));

        LOGGER.info("Персонажи последнего эпизода Морти: {}", characters);

        int idLastEpisodeCharacter = Steps.getId(characters.get(characters.size() - 1));

        Person jerry = Steps.getCharacterById(idLastEpisodeCharacter);

        LOGGER.info("Последний персонаж. {}", jerry.toString());

        LOGGER.info("Раса {}: {}. Раса {}: {}.", morty.getName(), morty.getSpecies(), jerry.getName(), jerry.getSpecies());

        LOGGER.info("Местонахождение {}: {}. Местонахождение {}: {}.", morty.getName(), morty.getLocation(), jerry.getName(), jerry.getLocation());

        assertEquals(morty.getSpecies(), jerry.getSpecies());

        assertNotEquals(morty.getLocation(), jerry.getLocation());

    }

}

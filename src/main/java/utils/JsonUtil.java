package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import java.nio.charset.StandardCharsets;

public class JsonUtil {

    /**
     * парсит к utf-8
     */
    public static String jsonToUtf(String text) {
        return new String(text.getBytes(), StandardCharsets.UTF_8);
    }

    /**
     * извлекает данные по json path из json
     */
    public static String getFieldFromJson(String body, String jsonPath) {
        Configuration jacksonConfig = Configuration.builder()
                .mappingProvider(new JacksonMappingProvider())
                .jsonProvider(new JacksonJsonProvider())
                .build();

        String val;
        JsonNode node;
        try {
            node = JsonPath.using(jacksonConfig).parse(body).read(jsonPath, JsonNode.class);
        } catch (InvalidJsonException e) {
            throw new InvalidJsonException("Невалидный json.");
        }

        if (node == null || node.isNull()) {
            val = "null";
        } else {
            val = node.toString();
        }

        String matchValue = RegexUtil.getMatchValueByGroupNumber(val, "^\"(.*)\"$", 1);
        val = matchValue == null ? val : matchValue;
        return val;
    }
}

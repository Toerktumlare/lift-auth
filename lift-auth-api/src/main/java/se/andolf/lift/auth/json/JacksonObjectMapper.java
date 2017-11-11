package se.andolf.lift.auth.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author torbjorn.hulten
 */
public enum JacksonObjectMapper {

    INSTANCE;

    private final ObjectMapper objectMapper;

    JacksonObjectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(NON_NULL);
        objectMapper.setSerializationInclusion(NON_DEFAULT);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(DeserializationFeature.USE_LONG_FOR_INTS);
        objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }

    public ObjectMapper objectMapper() {
        return objectMapper;
    }
}

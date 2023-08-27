package com.evo.apatrios.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ResourceUtils {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final LocalDateTimeDeserializer DATE_TIME_DESERIALIZER = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC")));
    private static final ObjectMapper OBJECT_MAPPER = (new ObjectMapper()).registerModule((new JavaTimeModule()).addDeserializer(LocalDateTime.class, DATE_TIME_DESERIALIZER)).setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    private ResourceUtils() {
    }

    public static <T> T parseJson(String resourcePath, Class<T> clazz) throws IOException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        return OBJECT_MAPPER.readValue(resource.getInputStream(), clazz);
    }

    public static <T> T parseJson(String resourcePath, TypeReference<T> typeReference) throws IOException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        return OBJECT_MAPPER.readValue(resource.getInputStream(), typeReference);
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

}

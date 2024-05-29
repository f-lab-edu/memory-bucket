package com.zephyr.api.controller;

import com.zephyr.api.request.ContentCreate;
import com.zephyr.api.request.MemoryCreate;
import com.zephyr.api.response.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.zephyr.api.utils.HttpRequestUtils.createUrl;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemoryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private MessageSource messageSource;

    @Test
    @DisplayName("기억 제목이 빈 문자열 / 기억 생성 / 400 반환")
    void givenEmptyMemoryTitle_whenCreateMemory_thenReturn400() {
        List<ContentCreate> contentCreates = new ArrayList<>();
        ContentCreate contentCreate = new ContentCreate("제목", "설명", "url", 1);
        contentCreates.add(contentCreate);
        MemoryCreate request = new MemoryCreate(
                1L,
                "",
                "설명",
                LocalDateTime.now(),
                new ArrayList<>(),
                contentCreates
        );

        //when
        ResponseEntity<ErrorResponse> result = restTemplate.postForEntity(
                createUrl(port, "/memories"),
                request,
                ErrorResponse.class
        );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(messageSource.getMessage("notBlank.memory.title", null, Locale.KOREA),
                result.getBody().getValidation().get("title"));
    }

    @Test
    @DisplayName("기억 제목이 공백일 때 / 기억 생성 / 400 반환")
    void givenBlankMemoryTitle_whenCreateMemory_thenReturn400() {
        List<ContentCreate> contentCreates = new ArrayList<>();
        ContentCreate contentCreate = new ContentCreate("제목", "설명", "url", 1);
        contentCreates.add(contentCreate);
        MemoryCreate request = new MemoryCreate(
                1L,
                "  ",
                "설명",
                LocalDateTime.now(),
                new ArrayList<>(),
                contentCreates
        );

        //when
        ResponseEntity<ErrorResponse> result = restTemplate.postForEntity(
                createUrl(port, "/memories"),
                request,
                ErrorResponse.class
        );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(messageSource.getMessage("notBlank.memory.title", null, Locale.KOREA),
                result.getBody().getValidation().get("title"));
    }

    @Test
    @DisplayName("기억 제목이 null / 기억 생성 / 400 반환")
    void givenNullMemoryTitle_whenCreateMemory_thenReturn400() {
        List<ContentCreate> contentCreates = new ArrayList<>();
        ContentCreate contentCreate = new ContentCreate("제목", "설명", "url", 1);
        contentCreates.add(contentCreate);
        MemoryCreate request = new MemoryCreate(
                1L,
                null,
                "설명",
                LocalDateTime.now(),
                new ArrayList<>(),
                contentCreates
        );

        //when
        ResponseEntity<ErrorResponse> result = restTemplate.postForEntity(
                createUrl(port, "/memories"),
                request,
                ErrorResponse.class
        );

        //then
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(messageSource.getMessage("notBlank.memory.title", null, Locale.KOREA),
                result.getBody().getValidation().get("title"));
    }
}

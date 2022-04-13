package com.hexagonal.template.interactor;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.hexagonal.template.entity.client.Client;
import com.hexagonal.template.interactor.client.ClientUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.boot.logging.LoggingSystem.ROOT_LOGGER_NAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class LogBookTest{

    private static MemoryAppender memoryAppender;

    @MockBean
    ClientUseCase clientService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        Logger logger = (Logger) LoggerFactory.getLogger(ROOT_LOGGER_NAME);
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryAppender);
        memoryAppender.start();
    }

    @AfterEach
    void cleanUp() {
        memoryAppender.reset();
        memoryAppender.stop();
    }


    @Test
    void shouldHideCpf() throws Exception {
        final var cpf = "00000000191";
        final var rg = "000000002";

        var client = Client.builder()
                .id(1L)
                .name("Random name")
                .age("18")
                .cpf(cpf)
                .rg(rg)
                .build();
        when(clientService.findClients())
                .thenReturn(Collections.singletonList(client));

        // WHEN
        var resultActions = this.mockMvc.perform(
                get("/v1/client")
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // THEN
        resultActions.andExpect(status().isOk());
        Assertions.assertFalse(memoryAppender.contains(cpf, Level.INFO));
        Assertions.assertFalse(memoryAppender.contains(cpf, Level.TRACE));
        Assertions.assertTrue(memoryAppender.contains("\"cpf\":\"XXX\"", Level.TRACE));

    }

}

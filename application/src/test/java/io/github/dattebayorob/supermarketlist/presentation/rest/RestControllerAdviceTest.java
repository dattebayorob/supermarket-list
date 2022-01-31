package io.github.dattebayorob.supermarketlist.presentation.rest;

import io.github.dattebayorob.supermarketlist.common.JsonUtil;
import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ErrorCode;
import io.github.dattebayorob.supermarketlist.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RestControllerAdviceTest {
    @Mock DummyService dummyService;
    MockMvc mockMvc;
    DummyController dummyController;

    @BeforeEach
    void beforeEach() {
        dummyController = new DummyController(dummyService);
        mockMvc = MockMvcBuilders.standaloneSetup(dummyController)
                .setControllerAdvice(new RestControllerAdvice())
                .build();
    }

    @Test
    void shouldHandleBusinessException() throws Exception {
        var dummy = new Dummy(UUID.randomUUID(), "Name");
        doThrow(new BusinessException("Generic Error")).when(dummyService).save(dummy);
        mockMvcPerfom(dummy)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", Matchers.equalTo(ErrorCode.GENERIC_BUSINESS_ERROR)));
    }

    @Test
    void shouldHandleResourceNotFoundException() throws  Exception {
        var dummy = new Dummy(UUID.randomUUID(), "Name");
        doThrow(new ResourceNotFoundException()).when(dummyService).save(dummy);
        mockMvcPerfom(dummy)
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldHandleUnexpectedErrors() throws Exception {
        String error = "Unexpected error!";
        var dummy = new Dummy(UUID.randomUUID(), "Name");
        doThrow(new RuntimeException(error)).when(dummyService).save(dummy);
        mockMvcPerfom(dummy)
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", Matchers.equalTo(ErrorCode.INTERNAL_SERVER_ERROR)))
                .andExpect(jsonPath("$.message", Matchers.equalTo(error)));
    }

    @Test
    void shouldHandlePropertyReferenceException() throws Exception {
        var propertyReferenceException = new PropertyReferenceException("name", ClassTypeInformation.from(Dummy.class), Collections.emptyList());
        var dummy = new Dummy(UUID.randomUUID(), "Name");
        doThrow(propertyReferenceException).when(dummyService).save(dummy);
        mockMvcPerfom(dummy)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", Matchers.equalTo(ErrorCode.REQUEST_PARAM_ERROR)))
                .andExpect(jsonPath("$.errors[0].field", Matchers.equalTo("name")));
    }

    private ResultActions mockMvcPerfom(Dummy dummy) throws Exception {
        return mockMvc.perform(post("/dummy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.serialize(dummy))
        );
    }

    @RestController
    @RequestMapping("/dummy")
    @RequiredArgsConstructor
    static class DummyController {
        private final DummyService dummyService;

        @PostMapping
        public Dummy save(@RequestBody @Valid Dummy dummy) {
            dummyService.save(dummy);
            return dummy;
        }

        @GetMapping
        public List<Dummy> findAll(
                @Valid @Size(min = 36, max=36) @RequestParam(value = "id", required = false) String id
        ) {
            return Collections.emptyList();
        }
    }
    static class DummyService {
        public void save(Dummy dummy) {}
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Dummy {
        @NotNull
        private UUID id;
        @NotEmpty
        private String name;
    }
}
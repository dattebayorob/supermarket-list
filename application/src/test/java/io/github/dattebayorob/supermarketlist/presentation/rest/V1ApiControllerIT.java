package io.github.dattebayorob.supermarketlist.presentation.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ProductCategoryRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({ "dev", "in-memory" })
class V1ApiControllerIT {
    static final String GET_PRODUCT_CATEGORIES = "/v1/categories";
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean ProductCategoryRepository productCategoryRepository;

    @Test
    void shouldGetProductCategoriesByName() throws Exception {
        final UUID uuid = UUID.randomUUID();
        final String name = "Bebidas";
        final ProductCategory category = new ProductCategory(uuid, name);
        when(productCategoryRepository.findByNameLike(name)).thenReturn(Arrays.asList(category));
        mockMvc.perform(get(GET_PRODUCT_CATEGORIES).param("name", name))
                .andExpect(status().isOk());
        verify(productCategoryRepository).findByNameLike(name);
    }

    @Test
    void shouldSaveANewCategory() throws Exception {
        final String name = "Nova categoria";
        final ProductCategoryRequest request = new ProductCategoryRequest().name(name);
        final ProductCategory productCategory = new ProductCategory();
        final UUID uuid = UUID.randomUUID();
        productCategory.setId(uuid);
        productCategory.setName(name);
        when(productCategoryRepository.save(any(ProductCategory.class))).thenReturn(productCategory);

        mockMvc.perform(post(GET_PRODUCT_CATEGORIES).content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(uuid.toString())))
                .andExpect(jsonPath("$.name", Matchers.equalTo(name)));

        verify(productCategoryRepository).save(any(ProductCategory.class));
    }

    @Test
    void shouldUpdateCategoryById() throws Exception {
        final UUID id = UUID.randomUUID();
        final String name = "Bebidas Atualizada";
        final ProductCategoryRequest request = new ProductCategoryRequest().name(name);

        final ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        productCategory.setName("Bebidas");

        when(productCategoryRepository.findById(id)).thenReturn(Optional.of(productCategory));
        when(productCategoryRepository.save(any(ProductCategory.class))).thenReturn(productCategory);

        mockMvc.perform(put(GET_PRODUCT_CATEGORIES.concat("/").concat(id.toString()))
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(id.toString())))
                .andExpect(jsonPath("$.name", Matchers.equalTo(name)));
        verify(productCategoryRepository).findById(id);
        verify(productCategoryRepository).save(any(ProductCategory.class));
    }
}
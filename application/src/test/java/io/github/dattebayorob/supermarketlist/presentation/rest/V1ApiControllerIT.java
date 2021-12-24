package io.github.dattebayorob.supermarketlist.presentation.rest;

import io.github.dattebayorob.supermarketlist.domain.ProductCategory;
import io.github.dattebayorob.supermarketlist.port.in.ProductCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class V1ApiControllerIT {
    static final String GET_PRODUCT_CATEGORIES = "/v1/categories";
    @Autowired MockMvc mockMvc;
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
}
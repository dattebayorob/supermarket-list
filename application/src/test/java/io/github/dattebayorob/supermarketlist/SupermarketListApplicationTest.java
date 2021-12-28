package io.github.dattebayorob.supermarketlist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({ "in-memory", "dev" })
class SupermarketListApplicationTest {
    @Test
    void contextLoads() {}
}
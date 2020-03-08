package com.example.tangboyang1;

import com.example.tangboyang1.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Tangboyang1ApplicationTests {
@Autowired
private ProductDao productDao;
    @Test
    void contextLoads() {

    }

}

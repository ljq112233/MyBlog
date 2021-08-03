package com.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class MyblogApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
    }
    @Test
    public void datebase() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());

    }

}

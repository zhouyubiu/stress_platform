package com.pressure.test.login;

//import com.pressure.test.login.pojo.port;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class LoginApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
//    port p;


    @Test
    void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
//        Connection connection=dataSource.getConnection();
//        System.out.println(connection);
//        connection.close();
//    p.getPort();
    }



}

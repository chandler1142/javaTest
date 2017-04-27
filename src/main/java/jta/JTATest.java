package jta;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by i325622 on 4/6/17.
 */
public class JTATest {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationcontext.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");
//        System.out.println(dataSource.toString());
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = dataSource.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

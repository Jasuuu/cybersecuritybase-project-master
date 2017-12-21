package sec.project;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.h2.tools.RunScript;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CyberSecurityBaseProjectApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(CyberSecurityBaseProjectApplication.class);
        // Open connection to database
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");

        try {
            // If database has not yet been created, create it
            RunScript.execute(connection, new FileReader("database-schema.sql"));
            //RunScript.execute(connection, new FileReader("database-import.sql"));
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}

package org.cascomio.springbatchexample.writters;

import org.cascomio.springbatchexample.models.Property;
import org.springframework.batch.item.ItemWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class HivePropertyWritter implements ItemWriter<Collection<Property>> {
    static Connection getConnection() throws Exception{
        Connection con = null;
        String HIVE_SERVER = System.getenv("HIVE_SERVER");
        String JDBC_DB_URL = "jdbc:hive2://"+HIVE_SERVER;
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            con =  DriverManager.getConnection(JDBC_DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public void write(List<? extends Collection<Property>> properties) throws Exception {
        Connection connection = getConnection();
        ResultSet result = connection.prepareCall("show databases;").executeQuery();
        System.out.println(result);
    }
}

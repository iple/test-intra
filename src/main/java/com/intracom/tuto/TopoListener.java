package com.intracom.tuto;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class TopoListener implements ServletContextListener {
    @Resource(lookup="java:jboss/datasources/ExampleDS")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection con = dataSource.getConnection()) {
            DatabaseMetaData metaData = con.getMetaData();
            try (ResultSet res = metaData.getCatalogs()) {
                System.out.println("Catalogs=" + res.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot find datasource");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}

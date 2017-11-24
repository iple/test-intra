package com.intracom.tuto;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

public class TopoListener implements ServletContextListener {
    @Resource(lookup="java:jboss/datasources/ExampleDS")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection con = dataSource.getConnection()) {
            TransactionManager txMgr = TransactionManagerLocator.get();
            txMgr.begin();
            try (Statement stmt = con.createStatement()){
                stmt.execute("CREATE TABLE IF NOT EXISTS element("
                        + "ip VARCHAR(15) NOT NULL, "
                        + "lon NUMERIC(11, 8) NOT NULL, "
                        + "lat NUMERIC(10, 8) NOT NULL, "
                        + "CONSTRAINT element_pk PRIMARY KEY(ip));");
                stmt.execute("CREATE TABLE IF NOT EXISTS link("
                        + "ipa varchar(15) NOT NULL, "
                        + "ipb varchar(15) NOT NULL, "
                        + "CONSTRAINT link_pk PRIMARY KEY(ipa, ipb))");
                txMgr.commit();
            }
            catch(SQLException exc) {
                txMgr.rollback();
                throw exc;
            }
        } catch (Exception e) {
            throw new RuntimeException("Initialization failed", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

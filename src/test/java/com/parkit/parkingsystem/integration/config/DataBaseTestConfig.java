package com.parkit.parkingsystem.integration.config;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.config.ReadPropertiesFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;


public class DataBaseTestConfig extends DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseTestConfig");

    public Connection getConnection() throws ClassNotFoundException, SQLException {

            ReadPropertiesFile readPropertiesFile = null;
            try {
                readPropertiesFile = new ReadPropertiesFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        logger.info("Create DB connection");
        Class.forName("com.mysql.cj.jdbc.Driver");
        assert readPropertiesFile != null;
        return DriverManager.getConnection(
                readPropertiesFile.getUrl2(),readPropertiesFile.getUsername(),readPropertiesFile.getPassword());
    }

    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.error("Error while closing connection",e);
            }
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
                logger.info("Closing Prepared Statement");
            } catch (SQLException e) {
                logger.error("Error while closing prepared statement",e);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
                logger.info("Closing Result Set");
            } catch (SQLException e) {
                logger.error("Error while closing result set",e);
            }
        }
    }
}

package com.parkit.parkingsystem.config;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ReadPropertiesFile {

    private Properties properties;

    public ReadPropertiesFile() throws IOException {

        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");

        this.properties = new Properties();

        properties.load(fis);

        fis.close();
    }

    public String getUsername () {

        return this.properties.getProperty("username");
    }
    public String getPassword () {

        return this.properties.getProperty("password");
    }
    public String getUrl1 () {

        return this.properties.getProperty("url1");
    }
    public String getUrl2 () {
        return this.properties.getProperty("url2");
    }
}

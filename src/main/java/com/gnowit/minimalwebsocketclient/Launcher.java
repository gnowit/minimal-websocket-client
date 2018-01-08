/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnowit.minimalwebsocketclient;

import com.gnowit.utils.logging.Logged;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.java_websocket.drafts.Draft_6455;

/**
 *
 * @author andrew
 */
public class Launcher extends Logged implements Runnable {

    @Override
    public void run() {
        log.info("Running app - begin loading properties from file {}", Constants.PROPERTIES_FILENAME);
        File propsFile = new File(Constants.PROPERTIES_FILENAME);

        Properties properties = new Properties();
        try {
            FileInputStream propsInput = new FileInputStream(propsFile);
            properties.load(propsInput);
        } catch (IOException ex) {
            log.error("ERROR loading properties.", ex);
        }

        log.info("Loaded properties: {}", properties);

        String serverUrl = properties.getProperty("url");
        String port = properties.getProperty("port");
        String path = properties.getProperty("path");

        String auth = properties.getProperty("authorization");
        Map<String, String> headers = new HashMap<>();
        if (auth != null) {
            headers.put("Authorization", auth);
        }

        String fullPath = serverUrl + ":" + port + path;
        try {
            URI serverLocation = new URI(fullPath);
            WSClient client = new WSClient(serverLocation, new Draft_6455(), headers);
            client.connect();
        } catch (URISyntaxException ex) {
            log.error("ERROR connecting to WebSocket server", ex);
        }

    }

    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
        Launcher launcher = new Launcher();
        launcher.run();
    }

}

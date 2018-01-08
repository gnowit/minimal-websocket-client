/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnowit.minimalwebsocketclient;

import com.gnowit.minimalwebsocketclient.messagehandlers.LoggingMessageHandler;
import com.gnowit.minimalwebsocketclient.messagehandlers.MessageHandler;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author andrew
 */
public class WSClient extends WebSocketClient {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private MessageHandler handler = new LoggingMessageHandler();
    private static final int TIMEOUT = 10000;
    
    public WSClient(MessageHandler handler, URI serverUri, Draft draft, Map<String, String> headers) {
        super(serverUri, draft, headers, TIMEOUT);
        if (handler != null) {
            this.handler = handler;
        }

        log.info("Initializing WSClient with URI {}", serverUri);
    }
    
    public WSClient(URI serverUri, Draft draft, Map<String, String> headers) {
        this(null, serverUri, draft, headers);
    }

    public WSClient(MessageHandler handler, URI serverUri, Draft draft) {
        super(serverUri, draft);
        if (handler != null) {
            this.handler = handler;
        }

        log.info("Initializing WSClient with URI {}", serverUri);
    }

    public WSClient(URI serverUri, Draft draft) {
        this(null, serverUri, draft);
    }

    public WSClient(MessageHandler handler, URI serverUri) {
        this(handler, serverUri, new Draft_6455());
    }

    public WSClient(URI serverUri) {
        this(null, serverUri);
    }

    @Override
    public void onOpen(ServerHandshake hsdata) {
        log.info("Opened WSClient connection.");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Closed WSClient connection with code {} // reason {}", code, reason);
    }

    @Override
    public void onMessage(String message) {
        handler.handleMessage(message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        String stringMessage = new String(message.array());
        handler.handleMessage(stringMessage);
    }

    @Override
    public void onError(Exception ex) {
        log.error("WSClient received error notification.", ex);

    }

}

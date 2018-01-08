/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnowit.minimalwebsocketclient.messagehandlers;

import com.gnowit.utils.logging.Logged;

/**
 *
 * @author andrew
 */
public class LoggingMessageHandler extends Logged implements MessageHandler {
    
    @Override
    public void handleMessage(String message){
        log.info("WebSocket message: {}", message);
    }
    
}

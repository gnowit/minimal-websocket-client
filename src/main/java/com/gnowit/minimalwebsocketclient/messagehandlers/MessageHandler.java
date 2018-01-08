/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnowit.minimalwebsocketclient.messagehandlers;

/**
 *
 * @author andrew
 */
public interface MessageHandler {
    
    public void handleMessage(String message);
    
}

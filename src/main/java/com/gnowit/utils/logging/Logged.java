/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnowit.utils.logging;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static javafx.scene.input.KeyCode.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author andrew
 */
public abstract class Logged {
    
    private static final Map<Class<?>, Logger> CLASS_LOGGERS = new ConcurrentHashMap<>();
    
    protected String className = getClass().getName();
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.concrete;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Valentino
 */
public class ConcreteMessageForServlet {
    private Map<String, Object> messages = null;
    
    /**
     *
     */
    public ConcreteMessageForServlet(){
        messages = new HashMap<String, Object>();
    }

    /**
     *
     * @param key
     * @return message associated to the key
     */
    public Object getMessage(String key) {
        return messages.get(key);
    }

    /**
     *
     * @param key
     * @param value
     */
    public void setMessage(String key, Object value) {
        messages.put(key, value);
    }
    
    /**
     *
     * @param key
     * @param newStructure
     * @return the result of put operation
     */
    public Object createSubStructure( String key, Map<String, Object> newStructure ){
        return messages.put(key, newStructure);
    }

}

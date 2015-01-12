/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.integrazione.model.Cycle;
import java.util.ArrayList;

/**
 *
 * @author Valentino
 */
public interface ICycle {
    
    public Cycle readCycle( int idCycle );
    public ArrayList<Cycle> getAllCycles();
     
}

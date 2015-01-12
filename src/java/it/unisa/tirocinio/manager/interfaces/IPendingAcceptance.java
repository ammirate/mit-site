/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.tirocinio.beans.PendingAcceptance;
import java.util.ArrayList;

/**
 *
 * @author johneisenheim
 */
public interface IPendingAcceptance {
    
    public PendingAcceptance readStudentInPendingAcceptance( int idPendingAcceptance );
    public ArrayList<PendingAcceptance> getAllStudentsInPendingAcceptance();
    
}

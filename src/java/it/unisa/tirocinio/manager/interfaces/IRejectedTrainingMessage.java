/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.tirocinio.beans.RejectedTrainingMessage;
import java.util.ArrayList;

/**
 *
 * @author johneisenheim
 */
public interface IRejectedTrainingMessage {
    
    public boolean createRejectedTrainingMessage( RejectedTrainingMessage aRejectedTrainingMessage );
    public boolean updateRejectedTrainingMessage( RejectedTrainingMessage aRejectedTrainingMessage );
    public boolean deleteOrganization( int idRejectedTrainingMessage );
    public RejectedTrainingMessage readTrainingMessage( int idRejectedTraingMessage );
    public RejectedTrainingMessage readLastTrainingMessage( String ssn );
    public ArrayList<RejectedTrainingMessage> getAllTrainingMessages();
    
}

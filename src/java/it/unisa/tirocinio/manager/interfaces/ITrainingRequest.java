/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.tirocinio.beans.TrainingRequest;
import it.unisa.tirocinio.beans.TrainingStatus;
import java.util.ArrayList;

/**
 *
 * @author johneisenheim
 */
public interface ITrainingRequest {
    
    public boolean createTrainingRequest( TrainingRequest aTrainingRequest );
    public boolean deleteTrainingRequest( int idTraininngRequest );
    public boolean updateTrainingRequest( TrainingRequest aTrainingRequest );
    
    public TrainingRequest readTrainingRequest( int aTrainingRequest );
    public ArrayList<TrainingRequest> readTrainingRequestByOrganization( String VATNumber );
    public ArrayList<TrainingRequest> readTrainingRequestByProfessor( String SSN );
    public TrainingRequest readTrainingRequestByStudent(String SSN);
    
    public ArrayList<TrainingRequest> getAllTrainingRequests();
    public boolean changeTrainingStatus( int idTrainingRequest, TrainingStatus aStatus );
    public boolean isInternship( int idTrainingRequest );
    public ArrayList<TrainingRequest> getAllInternships();
}

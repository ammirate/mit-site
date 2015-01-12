/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.tirocinio.beans.ViewsResult;



/**
 *
 * @author carlosborges
 */
public interface IView {
    /**
     * Return the number of student started or ended a trainingship into an 
     * organization
     * @param OrgVatNumber
     * @return 
     */
    public ViewsResult getNumberOfTrainingForOrganization(String OrgVatNumber);
    
    /**
     * Return all student who have finished the training
     * @return 
     */
    public ViewsResult getAdminStudentEndedTraining();
    
    /**
     * Return all student who start training request
     * @return 
     */
    public ViewsResult getAdminStudentActiveTraining();
    
    
    /**
     * Return all student who end the training but not yet sed the 
     * questionaire
     * @return 
     */
    public ViewsResult getAdminStudentWaitEnded();
    
     /**
     * Return all student who don't start trainig and don't send
     * the documentation to start the training
     * @return 
     */
    public ViewsResult getStudentInactiveTraining();
    
    
     /**
     * Return student associated to professor who have finished the training
     * @param SSN
     * @return 
     */
    public ViewsResult getProfessorStudentEndedTraining(String SSN);
    
    /**
     * Return associated to professor student who start training request
     * @param SSN
     * @return 
     */
    public ViewsResult getProfessorStudentActiveTraining(String SSN);
    
    
    /**
     * Return associated to professor student who end the training but not yet sed the 
     * questionaire
     * @param SSN
     * @return 
     */
    public ViewsResult getProfessorStudentWaitEnded(String SSN);
    

    
    
    
}

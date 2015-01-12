/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.tirocinio.manager.interfaces;

import it.unisa.tirocinio.beans.TrainingOffer;
import java.util.ArrayList;

/**
 *
 * @author johneisenheim
 */
public interface ITrainingOffer {
    
    public boolean createInnerTrainingOffer( TrainingOffer aTrainingOffer );
    public boolean createOuterTrainingOffer( TrainingOffer aTrainingOffer );
    public boolean updateTrainingOffer( TrainingOffer aTrainingOffer );
    public boolean deleteTrainingOffer( int idTrainingOffer );
    
    public TrainingOffer readTrainingOffer( int idTrainingOffer );
    public ArrayList<TrainingOffer> readInnerTrainingOffer( String personSSN );
    public ArrayList<TrainingOffer> readOuterTrainingOffer( String vatNumber );
    
    public ArrayList<TrainingOffer> getAllTrainingOffers();
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.offerta_formativa.moodle.moodle_rest;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class MoodleRestStringsException extends MoodleRestException implements Serializable {
  
  public MoodleRestStringsException(String string) {
  }

  MoodleRestStringsException() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}

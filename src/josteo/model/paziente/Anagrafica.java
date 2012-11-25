/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.paziente;

import java.util.*;
import josteo.infrastructure.DomainBase.IValueObject;

/**
 *
 * @author cristiano
 */
public class Anagrafica implements IValueObject {
    private String _nome;
    private String _cognome;
    private Date _dataNascita;
    private String _professione;

    public String get_Nome(){ return this._nome; }
    public String get_Cognome(){ return this._cognome; }
    public Date get_DataDiNascita(){ return this._dataNascita; }
    public String get_Professione(){ return this._professione; }

    public Anagrafica(String nome, String cognome, Date dataNascita, String professione){
        this._nome = nome;
        this._cognome = cognome;
        this._dataNascita = dataNascita;
        this._professione = professione;
    }

}

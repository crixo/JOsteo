/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.paziente;

import josteo.infrastructure.DomainBase.IValueObject;

/**
 *
 * @author cristiano
 */
public class Address implements IValueObject {
    private String _indirizzo;
    private String _citta;
    private String _telefono;
    private String _celluare;
    private String _prov;
    private String _cap;
    private String _email;

    public Address(String indirizzo, String citta, String telefono, String celluare, String prov, String cap, String email){
        this._indirizzo = indirizzo;
        this._citta = citta;
        this._telefono = telefono;
        this._celluare = celluare;
        this._prov = prov;
        this._cap = cap;
        this._email = email;
    }

    public String get_indirizzo(){ return this._indirizzo;}
    public String get_citta(){ return this._citta;}
    public String get_telefono(){ return this._telefono;}
    public String get_celluare(){ return this._celluare;}
    public String get_prov(){ return this._prov;}
    public String get_cap(){ return this._cap;}
    public String get_email(){ return this._email;}

}

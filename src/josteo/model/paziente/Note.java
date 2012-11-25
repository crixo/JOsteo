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
public class Note implements IValueObject {
    private String _descr;
    private Date _data;

    public Note(String descr, Date data){
        this._descr = descr;
        this._data = data;
    }

    public String get_Descrizione(){ return this._descr;}
    public Date get_Data(){ return this._data;}

}

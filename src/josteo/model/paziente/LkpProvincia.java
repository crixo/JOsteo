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
public class LkpProvincia implements IValueObject {
    private String _descr;
    private String _sigla;

    public LkpProvincia(String sigla, String descr){
        this._descr = descr;
        this._sigla = sigla;
    }

    public String getDescrizione(){ return this._descr;}
    public String getSigla(){ return this._sigla;}

    public String toString(){
        return this._descr;
    }
}

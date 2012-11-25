/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.paziente;

import josteo.infrastructure.DomainBase.*;
import josteo.model.tipoEsame.TipoEsame;

/**
 *
 * @author cristiano
 */
public class Esame extends EntityBase {
    private Note _note;
    private TipoEsame _tipoEsame;

    public Note get_Note(){ return this._note;}
    public void set_Note(Note val){
        if(val!=null && !val.equals(_note)){
            this._note = val;
            this.set_IsChanged(true);
        }
    }

    public TipoEsame get_TipoEsame(){ return this._tipoEsame;}
    public void set_TipoEsame(TipoEsame val){
        if(val!=null && !val.equals(this._tipoEsame)){
            this._tipoEsame = val;
            this.set_IsChanged(true);
        }
    }

    public Esame(){
        this(0);
    }

    public Esame(int key){
        this(key, null, null);
    }

    public Esame(int key, Note note, TipoEsame tipoEsame){
        super(key);
        this._note = note;
        this._tipoEsame = tipoEsame;
    }

    @Override
    protected BrokenRuleMessages GetBrokenRuleMessages(){
        return new ValutazioneRuleMessages();
    }

    @Override
    protected void Validate(){
        if(this._note==null){
            this.get_BrokenRules().add(new BrokenRule("Note","cannot be null"));
        }

        if(this._note!=null && this._note.get_Descrizione().length()==0){
            this.get_BrokenRules().add(new BrokenRule("Descrizione","is mandatory"));
        }

        if(this._note!=null && this._note.get_Data() == null){
            this.get_BrokenRules().add(new BrokenRule("Data","cannot be null"));
        }

        if(this._tipoEsame==null){
            this.get_BrokenRules().add(new BrokenRule("Tipo Esame","cannot be null"));
        }

        if(this._tipoEsame!=null && this._tipoEsame.getDescription().length()==0){
            this.get_BrokenRules().add(new BrokenRule("TipoEsame.Descriptione","cannot be empty"));
        }

    }
}

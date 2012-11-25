/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.paziente;

import josteo.infrastructure.DomainBase.*;
import josteo.model.tipoAnamnesi.TipoAnamnesi;

/**
 *
 * @author cristiano
 */
public class AnamnesiRemota extends EntityBase {
    private Note _note;
    private TipoAnamnesi _tipo;

    public Note get_Note(){ return this._note;}
    public void set_Note(Note val){
        if(val!=null && !val.equals(this._note)){
            this._note=val;
            this.set_IsChanged(true);
        }
    }

    public TipoAnamnesi get_Tipo(){ return this._tipo;}
    public void set_Tipo(TipoAnamnesi val){
        if(val!=null && !val.equals(this._tipo)){
            this._tipo=val;
            this.set_IsChanged(true);
        }
    }

    public AnamnesiRemota(){
        this(0);
    }

    public AnamnesiRemota(int key){
        this(key, null, null);
    }

    public AnamnesiRemota(int key, Note note, TipoAnamnesi tipo){
        super(key);
        this._note = note;
        this._tipo = tipo;
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
            this.get_BrokenRules().add(new BrokenRule("Descrizione","cannot be empty"));
        }

        if(this._note!=null && this._note.get_Data() == null){
            this.get_BrokenRules().add(new BrokenRule("Data","cannot be null"));
        }

        if(this._tipo==null){
            this.get_BrokenRules().add(new BrokenRule("TipoAnmesi","is mandatory"));
        }

        if(this._tipo!=null && this._tipo.get_Key()==null){
            this.get_BrokenRules().add(new BrokenRule("TipoAnmesi.Key","cannot be null"));
        }

    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.paziente;

import josteo.infrastructure.DomainBase.*;

/**
 *
 * @author cristiano
 */
public class Trattamento extends EntityBase {

    private Note _note;
    
    public Note get_Note(){ return this._note;}
    public void set_Note(Note val){
        if(val!=null && !val.equals(_note)){
            this._note = val;
            this.set_IsChanged(true);
        }
    }


    public Trattamento(){
        this(0,null);
    }

    public Trattamento(Note note){
        this(0,note);
    }

    public Trattamento(int key, Note note){
        super(key);
        this._note = note;
    }

    @Override
    protected BrokenRuleMessages GetBrokenRuleMessages(){
        return new ValutazioneRuleMessages();
    }

    @Override
    protected void Validate(){
        if(this._note.get_Descrizione().length()==0){
            this.get_BrokenRules().add(new BrokenRule("Descrizione","is mandatory"));
        }

        if(this._note.get_Data() == null){
            this.get_BrokenRules().add(new BrokenRule("Data","is mandatory"));
        }
    }
}

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
public class Valutazione extends EntityBase {
    private String _strutturale;
    private String _cranioSacrale;
    private String _akOrtodontica;


    public String get_Strutturale(){ return this._strutturale;}
    public void set_Strutturale(String val){
        if(this._strutturale.compareTo(val)!=0){
            this._strutturale = val;
            this.set_IsChanged(true);
        }
    }

    public String get_CranioSacrale(){ return this._cranioSacrale;}
    public void set_CranioSacrale(String val){
        if(this._cranioSacrale.compareTo(val)!=0){
            this._cranioSacrale = val;
            this.set_IsChanged(true);
        }
    }

    public String get_AkOrtodontica(){ return this._akOrtodontica;}
    public void set_AkOrtodontica(String val){
        if(this._akOrtodontica.compareTo(val)!=0){
            this._akOrtodontica = val;
            this.set_IsChanged(true);
        }
    }

    public Valutazione(){
        this(0,"","","");
    }

    public Valutazione(String strutturale, String cranioSacrale, String akOrtodontica){
        this(0,strutturale, cranioSacrale, akOrtodontica);
    }

    public Valutazione(int key, String strutturale, String cranioSacrale, String akOrtodontica){
        super(key);
        this._strutturale=strutturale;
        this._cranioSacrale=cranioSacrale;
        this._akOrtodontica=akOrtodontica;
    }

    @Override
    protected BrokenRuleMessages GetBrokenRuleMessages(){
        return new ValutazioneRuleMessages();
    }

    @Override
    protected void Validate(){
        if(this._strutturale.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Strutturale","is mandatory"));
        }

        if(this._cranioSacrale.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Cranio Sacrale","is mandatory"));
        }

        if(this._akOrtodontica.length()==0){
            this.get_BrokenRules().add(new BrokenRule("AK Ortodontica","is mandatory"));
        }
    }
}

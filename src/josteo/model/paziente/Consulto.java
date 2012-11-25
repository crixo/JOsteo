/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.paziente;

import java.util.*;

import josteo.infrastructure.DomainBase.*;

/**
 *
 * @author cristiano
 */
public class Consulto extends EntityBase {
    private Date _data;
    private String _problemaIniziale;
    private List<AnamnesiProssima> _anamesiProssime;
    private List<Valutazione> _valutazioni;
    private List<Trattamento> _trattamenti;
    private List<Esame> _esami;

    public Date get_Data(){ return this._data;}
    public void set_Data(Date val){
        if(val!=this._data){
            this._data = val;
            this.set_IsChanged(true);
        }
    }

    public String get_ProblemaIniziale(){ return this._problemaIniziale;}
    public void set_ProblemaIniziale(String val){  
        if(!this._problemaIniziale.equals(val)){
            this._problemaIniziale = val;
            this.set_IsChanged(true);
        }
    }

    public List<Trattamento> get_Trattamenti(){ return this._trattamenti;}

    public List<Valutazione> get_Valutazioni(){ return this._valutazioni;}

    public List<AnamnesiProssima> get_AnamesiProssime(){ return this._anamesiProssime;}

    public List<Esame> get_Esami(){ return this._esami;}

    public Consulto(){
        this(0, new Date(), "");
    }

    public Consulto(int key, Date data, String problemaIniziale){
        super(key);
        this._data = data;
        this._problemaIniziale = problemaIniziale;
        this._anamesiProssime = new ArrayList<AnamnesiProssima>();
        this._trattamenti = new ArrayList<Trattamento>();
        this._valutazioni = new ArrayList<Valutazione>();
        this._esami = new ArrayList<Esame>();
    }

    @Override
    protected BrokenRuleMessages GetBrokenRuleMessages(){
        return new ValutazioneRuleMessages();
    }

    @Override
    protected void Validate(){
        if(this._problemaIniziale.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Problema Iniziale","is mandatory"));
        }

        if(this._data==new Date()){
            this.get_BrokenRules().add(new BrokenRule("Data","is mandatory"));
        }
    }

}

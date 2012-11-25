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
public class AnamnesiProssima extends EntityBase {

    private String _primaVolta;
    private String _tipologia;
    private String _localizzazione;
    private String _irradiazione;
    private String _periodoInsorgenza;
    private String _durata;
    private String _familiarita;
    private String _altreTerapie;
    private String _varie;

    public String get_PrimaVolta(){ return this._primaVolta; }
    public void set_PrimaVolta(String val){
        if(this._primaVolta.compareTo(val)!=0){
            this._primaVolta = val;
            this.set_IsChanged(true);
        }
    }

    public String get_Tipologia(){ return this._tipologia; }
    public void set_Tipologia(String val){
        if(this._tipologia.compareTo(val)!=0){
            this._tipologia = val;
            this.set_IsChanged(true);
        }
    }

    public String get_Localizzazione(){ return this._localizzazione; }
    public void set_Localizzazione(String val){
        if(this._localizzazione.compareTo(val)!=0){
            this._localizzazione = val;
            this.set_IsChanged(true);
        }
    }

    public String get_Irradiazione(){ return this._irradiazione; }
    public void set_Irradiazione(String val){
        if(this._irradiazione.compareTo(val)!=0){
            this._irradiazione = val;
            this.set_IsChanged(true);
        }
    }

    public String get_PeriodoInsorgenza(){ return this._periodoInsorgenza; }
    public void set_PeriodoInsorgenza(String val){
        if(this._periodoInsorgenza.compareTo(val)!=0){
            this._periodoInsorgenza = val;
            this.set_IsChanged(true);
        }
    }

    public String get_Durata(){ return this._durata; }
    public void set_Durata(String val){
        if(this._durata.compareTo(val)!=0){
            this._durata = val;
            this.set_IsChanged(true);
        }
    }

    public String get_Familiarita(){ return this._familiarita; }
    public void set_Familiarita(String val){
        if(this._familiarita.compareTo(val)!=0){
            this._familiarita = val;
            this.set_IsChanged(true);
        }
    }

    public String get_AltreTerapie(){ return this._altreTerapie; }
    public void set_AltreTerapie(String val){
        if(this._altreTerapie.compareTo(val)!=0){
            this._altreTerapie = val;
            this.set_IsChanged(true);
        }
    }
    public String get_Varie(){ return this._varie; }
    public void set_Varie(String val){
        if(this._varie.compareTo(val)!=0){
            this._varie = val;
            this.set_IsChanged(true);
        }
    }

    public AnamnesiProssima(){
        this(0);
    }

    public AnamnesiProssima(int key){
        this(key, "", "", "", "", "", "", "", "", "");
    }

    public AnamnesiProssima(int key, String primaVolta, String tipologia, String localizzazione, String irradiazione, String periodoInsorgenza, String durata, String familiarita, String altreTerapie, String varie){
        super(key);
        this._primaVolta = primaVolta;
        this._tipologia = tipologia;
        this._localizzazione = localizzazione;
        this._irradiazione = irradiazione;
        this._periodoInsorgenza = periodoInsorgenza;
        this._durata = durata;
        this._familiarita = familiarita;
        this._altreTerapie = altreTerapie;
        this._varie = varie;
    }

    @Override
    protected BrokenRuleMessages GetBrokenRuleMessages(){
        return new ValutazioneRuleMessages();
    }

    @Override
    protected void Validate(){
        if(this._primaVolta.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Prima Volta","is mandatory"));
        }
    }


}

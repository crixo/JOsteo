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
public class Paziente extends EntityBase implements IAggregateRoot{
    private List<Consulto> _consulti;
    private Anagrafica _anagrafica;
    private Address _address;
    private List<AnamnesiRemota> _anamnesiRemote;
    private String _nome;
    private String _cognome;
    private Date _dataNascita;
    private String _professione;
    private String _indirizzo;
    private String _citta;
    private String _cap;
    private String _prov;
    private String _telefono;
    private String _cellulare;
    private String _email;
    
    public String getNome(){return this._nome;}
    public void setNome(String val){
        if(val.compareTo(this._nome)!=0){
            this._nome=val;
            this.set_IsChanged(true);
        }
    }

    public String getCognome(){return this._cognome;}
    public void setCognome(String val){
        if(val.compareTo(this._cognome)!=0){
            this._cognome=val;
            this.set_IsChanged(true);
        }
    }

    public Date getDataNascita(){return this._dataNascita;}
    public void setDataNascita(Date val){
        if(val.compareTo(this._dataNascita)!=0){
            this._dataNascita=val;
            this.set_IsChanged(true);
        }
    }

    public String getProfessione(){return this._professione;}
    public void setProfessione(String val){
        if(val.compareTo(this._professione)!=0){
            this._professione=val;
            this.set_IsChanged(true);
        }
    }

    public String getIndirizzo(){return this._indirizzo;}
    public void setIndirizzo(String val){
        if(val.compareTo(this._indirizzo)!=0){
            this._indirizzo=val;
            this.set_IsChanged(true);
        }
    }

    public String getCitta(){return this._citta;}
    public void setCitta(String val){
        if(val.compareTo(this._citta)!=0){
            this._citta=val;
            this.set_IsChanged(true);
        }
    }

    public String getCap(){return this._cap;}
    public void setCap(String val){
        if(val.compareTo(this._cap)!=0){
            this._cap=val;
            this.set_IsChanged(true);
        }
    }

    public String getProv(){return this._prov;}
    public void setProv(String val){
        if(val.compareTo(this._prov)!=0){
            this._prov=val;
            this.set_IsChanged(true);
        }
    }

    public String getTelefono(){return this._telefono;}
    public void setTelefono(String val){
        if(val.compareTo(this._telefono)!=0){
            this._telefono=val;
            this.set_IsChanged(true);
        }
    }

    public String getCellulare(){return this._cellulare;}
    public void setCellulare(String val){
        if(val.compareTo(this._cellulare)!=0){
            this._cellulare=val;
            this.set_IsChanged(true);
        }
    }

    public String getEmail(){return this._email;}
    public void setEmail(String val){
        if(val.compareTo(this._email)!=0){
            this._email=val;
            this.set_IsChanged(true);
        }
    }

    public String getFullName(){
        return this.getCognome() + ", " + this.getNome();
    }

    public String getFullAddress(){
        return this.getIndirizzo() + ", "
                + this.getCap() + " "
                + this.getCitta()
                + "(" + this.getProv() + ")";
    }

//    public Anagrafica get_Anagrafica(){ return this._anagrafica;}
//    public void set_Anagrafica(Anagrafica val){
//        if(!val.compareTo(this._anagrafica)){
//            this._anagrafica=val;
//            this.set_IsChanged(true);
//        }
//    }
//
//    public Address get_Address(){ return this._address;}
//    public void set_Address(Address val){
//        if(!val.compareTo(this._address)){
//            this._address=val;
//            this.set_IsChanged(true);
//        }
//    }

    public List<Consulto> get_Consulti(){ return this._consulti;}

    public List<AnamnesiRemota> get_AnamnesiRemote(){ return this._anamnesiRemote;}

    public Paziente(){
        this(0);
    }

    public Paziente(int key){//, Anagrafica anagrafica, Address address
        super(key);
//        this._anagrafica = anagrafica;
//        this._address = address;
        this._consulti = new ArrayList<Consulto>();
        this._anamnesiRemote = new ArrayList<AnamnesiRemota>();

        this._nome="";
        this._cognome="";
        this._dataNascita=new Date();
        this._professione="";
        this._indirizzo="";
        this._citta="";
        this._cap="";
        this._prov="";
        this._telefono="";
        this._cellulare="";
        this._email="";
    }

    @Override
    protected BrokenRuleMessages GetBrokenRuleMessages(){
        return new ValutazioneRuleMessages();
    }

    @Override
    protected void Validate(){
//        if(this._anagrafica==null){
//            this.get_BrokenRules().add(new BrokenRule("",""));
//        }
//
//        if(this._address==null){
//            this.get_BrokenRules().add(new BrokenRule("",""));
//        }

        if(this._nome.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Nome","cannot be empty"));
        }

        if(this._cognome.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Cognome","cannot be empty"));
        }

        if(this._dataNascita==new Date()){
            this.get_BrokenRules().add(new BrokenRule("Data di nascita","is missing"));
        }

        if(this._professione.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Professione","cannot be empty"));
        }

//        if(this._address!=null && this._address.get_indirizzo().length()==0){
//            this.get_BrokenRules().add(new BrokenRule("",""));
//        }

        if(this._citta.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Citta'","cannot be empty"));
        }

        if(this._prov.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Provincia","cannot be empty"));
        }

    }
}

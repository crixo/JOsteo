/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.viewmodels;

import josteo.model.paziente.*;
import josteo.application.*;
import josteo.infrastructure.helpers.*;
import java.util.*;

/**
 *
 * @author cristiano
 */
public class PazientePresenter {
    private Paziente _paziente;
    private josteo.application.IPazienteService _svc;

    public Object getKey(){return this._paziente.get_Key();}

    public String getNome(){return this._paziente.getNome();}
    public void setNome(String val){ this._paziente.setNome(val);}

    public String getCognome(){return this._paziente.getCognome();}
    public void setCognome(String val){ this._paziente.setCognome(val);}

    public Date getDataNascita(){return this._paziente.getDataNascita();}
    public void setDataNascita(Date val){ this._paziente.setDataNascita(val);}

    public String getProfessione(){return this._paziente.getProfessione();}
    public void setProfessione(String val){ this._paziente.setProfessione(val);}

    public String getIndirizzo(){return this._paziente.getIndirizzo();}
    public void setIndirizzo(String val){ this._paziente.setIndirizzo(val);}

    public String getCitta(){return this._paziente.getCitta();}
    public void setCitta(String val){ this._paziente.setCitta(val);}

    public String getCap(){return this._paziente.getCap();}
    public void setCap(String val){ this._paziente.setCap(val);}

    public String getProv(){return this._paziente.getProv();}
    public void setProv(String val){ this._paziente.setProv(val);}

    public String getTelefono(){return this._paziente.getTelefono();}
    public void setTelefono(String val){ this._paziente.setTelefono(val);}

    public String getCellulare(){return this._paziente.getCellulare();}
    public void setCellulare(String val){ this._paziente.setCellulare(val);}

    public String getEmail(){return this._paziente.getEmail();}
    public void setEmail(String val){ this._paziente.setEmail(val);}

    public List<LkpProvincia> getProvince(){return _svc.GetAllProvince();}

    
    public String getFullName(){
        return _paziente.getCognome() + ", " + _paziente.getNome();
    }

    public String getFullAddress(){
        return _paziente.getIndirizzo() + ", "
                + _paziente.getCap() + " "
                + _paziente.getCitta()
                + "(" + _paziente.getProv() + ")";
    }

    public Paziente getEntity(){
        return this._paziente;
    }
    
    public PazientePresenter(){
        this(new Paziente());
    }

    public PazientePresenter(Paziente paziente){
        this._paziente = paziente;
        _svc = (IPazienteService) ContainerHelper.getContainer().getBean("PazienteService");
    }

    public void Store(){
        _svc.SavePaziente(this._paziente);

    }
    
    public void Delete(){
        _svc.DeletePaziente(this._paziente);

    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.viewmodels;

import java.util.*;

import josteo.model.paziente.*;
import josteo.application.*;
import josteo.infrastructure.helpers.*;
import josteo.infrastructure.DomainBase.*;
import josteo.infrastructure.UI.*;



/**
 *
 * @author cristiano
 */
public class ValutazioniPresenter extends ListPresenterBase<Valutazione>{
    public ValutazioniPresenter(){
        super();
    }
    
    @Override
    protected void createEmptyItem(){
        this.NewItem = new Valutazione();
    }

    @Override
    public List<Valutazione> GetList(){
        Consulto consulto = josteo.application.ApplicationState.getInstance().getSelectedConsulto();
        return consulto.get_Valutazioni();
    }

    @Override
    public void Load(){
        Consulto consulto = josteo.application.ApplicationState.getInstance().getSelectedConsulto();
        if(consulto==null) return;
        super.Load();
    }

}

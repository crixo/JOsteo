/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.viewmodels;

import josteo.infrastructure.DomainBase.*;
import java.util.*;
import josteo.model.paziente.*;
import josteo.application.*;
import josteo.infrastructure.helpers.*;

/**
 *
 * @author cristiano
 */
public class ConsultiPresenter extends ListPresenterBase<Consulto>{
    
    public ConsultiPresenter(){
        super();
    }

    @Override
    protected void createEmptyItem(){
        this.NewItem = new Consulto();
    }

    public String getPaziente(){
        Paziente paziente = josteo.application.ApplicationState.getInstance().getCurrentPaziente();
        if(paziente!=null)
            return paziente.getFullName();
        return "";
    }

    @Override
    public List<Consulto> GetList(){
        Paziente paziente = josteo.application.ApplicationState.getInstance().getCurrentPaziente();
        return paziente.get_Consulti();
    }

    @Override
    public void Load(){
        Paziente paziente = josteo.application.ApplicationState.getInstance().getCurrentPaziente();
        if(paziente==null) return;
        super.Load();
    }

}

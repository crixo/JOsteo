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
public class TrattamentiPresenter extends ListPresenterBase<Trattamento>{
    public TrattamentiPresenter(){
        super();
    }

    @Override
    protected void createEmptyItem(){
        this.NewItem = new Trattamento();
    }

    @Override
    public List<Trattamento> GetList(){
        Consulto consulto = josteo.application.ApplicationState.getInstance().getSelectedConsulto();
        return consulto.get_Trattamenti();
    }

    @Override
    public void Load(){
        Consulto consulto = josteo.application.ApplicationState.getInstance().getSelectedConsulto();
        if(consulto==null) return;
        super.Load();
    }

}

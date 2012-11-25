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

import ca.odell.glazedlists.*;

/**
 *
 * @author cristiano
 */
public class AnamnesiProssimaPresenter extends ListPresenterBase<AnamnesiProssima>{

    
    public AnamnesiProssimaPresenter(){
        super();
    }

    @Override
    protected void createEmptyItem(){
        this.NewItem = new AnamnesiProssima();
    }

    @Override
    public List<AnamnesiProssima> GetList(){
        Consulto consulto = josteo.application.ApplicationState.getInstance().getSelectedConsulto();
        return consulto.get_AnamesiProssime();
    }

    @Override
    public void Load(){
        Consulto consulto = josteo.application.ApplicationState.getInstance().getSelectedConsulto();
        if(consulto==null) return;
        super.Load();
    }

}

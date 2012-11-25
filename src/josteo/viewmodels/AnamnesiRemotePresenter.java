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
public class AnamnesiRemotePresenter extends ListPresenterBase<AnamnesiRemota>{

    
    public AnamnesiRemotePresenter(){
        super();
    }

    @Override
    protected void createEmptyItem(){
        this.NewItem = new AnamnesiRemota();
    }

    @Override
    public List<AnamnesiRemota> GetList(){
        Paziente paziente = josteo.application.ApplicationState.getInstance().getCurrentPaziente();
        return paziente.get_AnamnesiRemote();
    }

    @Override
    public void Load(){
        Paziente paziente = josteo.application.ApplicationState.getInstance().getCurrentPaziente();
        if(paziente==null) return;
        super.Load();
    }

}

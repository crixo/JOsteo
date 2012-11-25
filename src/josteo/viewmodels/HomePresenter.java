/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.viewmodels;

import java.util.*;
import josteo.model.paziente.*;
import josteo.application.*;
import josteo.infrastructure.helpers.*;
import java.util.regex.*;

import ca.odell.glazedlists.*;
/**
 *
 * @author cristiano
 */
public class HomePresenter {

    private String _cognome;
    private EventList<PazientePresenter> _pazientiP;
    private String _filter;
    private List<Paziente> _pazienti;
    private PazientePresenter _selPazienteP;

    public void set_Cognome(String val){ this._cognome = val; }
    public void set_Filter(String val){ 
        this._filter = val;
        this._pazientiP.clear();
        for(Paziente p : _pazienti)
            if(val.length() == 0 || clean(p.getCognome()).indexOf(clean(val))==0)
                this._pazientiP.add( new PazientePresenter(p) );
    }

    private String clean(String input) {
        input = input.toLowerCase();

        String patternStr = "[^a-z]";
        String replacementStr = "";

        // Compile regular expression
        Pattern pattern = Pattern.compile(patternStr);

        // Replace all occurrences of pattern in input
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll(replacementStr);

        return input;
    }
    
    public PazientePresenter get_SelectedPaziente(){ return this._selPazienteP; }
    public void set_SelectedPaziente(PazientePresenter val){ this._selPazienteP = val; }

    public EventList<PazientePresenter> get_Pazienti(){ return this._pazientiP; }

    public HomePresenter(){
        this._pazientiP = new BasicEventList<PazientePresenter>();
        this._filter="";
    }

    public void LoadPazienti(){
        this._pazientiP.clear();
        IPazienteService svc = (IPazienteService) ContainerHelper.getContainer().getBean("PazienteService");
        _pazienti = svc.GetAllPazienti();
        for(Paziente p : _pazienti)
            if(_filter.length() == 0 || p.getCognome().toLowerCase().indexOf(_filter.toLowerCase())==0)
                this._pazientiP.add( new PazientePresenter(p) );
    }

}

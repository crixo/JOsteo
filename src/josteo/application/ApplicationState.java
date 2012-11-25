/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.application;

import java.util.*;
import josteo.model.paziente.*;
import josteo.infrastructure.UI.IView;

/**
 *
 * @author cristiano
 */
public class ApplicationState {
    private static ApplicationState singleton;

    public static ApplicationState getInstance() {
      if(singleton == null) {
         synchronized(ApplicationState.class) {
           if(singleton == null) {
             singleton = new ApplicationState();
           }
        }
      }
      return singleton;
    }
    
    private Paziente _currentPaziente;
    private IView _currentView;
    private int _selConsultoID;
    private Consulto _selConsulto;
    ArrayList _pazienteSelectedListeners;
    ArrayList _consultoSelectedListeners;

    
    public Paziente getCurrentPaziente(){ return this._currentPaziente; }
    public void setCurrentPaziente(Paziente val){ 
        this._currentPaziente=val;
        this.setSelectedConsulto(null);
        fireEvent(val);
    }

    public void setSelectedConsulto(Consulto val){
        this._selConsulto=val;
        fireEvent(val);
    }
    public Consulto getSelectedConsulto(){
        return this._selConsulto;
    }

//    public void setSelectedConsultoID(int val){
//        this._selConsultoID=val;
//    }
//    public Consulto getSelectedConsulto(){
//        if(this._selConsultoID>0 && _currentPaziente!=null && _currentPaziente.get_Consulti().size()>0){
//        for(Consulto c : _currentPaziente.get_Consulti())
//            if( (Integer)c.get_Key() == this._selConsultoID )
//                return c;
//        }
//        return null;
//    }

    public IView getCurrentView(){ return this._currentView; }
    public void setCurrentView(josteo.infrastructure.UI.IView val){
        this._currentView=val;
    }



    private ApplicationState(){
        this._selConsultoID=0;
        _pazienteSelectedListeners=new ArrayList();
        _consultoSelectedListeners=new ArrayList();
    }

    public void SwitchView(String sView2Show){
        final IView view2Show = (IView)josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean(sView2Show);
        //if(_currentView!=null) _currentView.dispose();

        //_currentView.setVisible(false);
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                view2Show.setVisible(true);
//                view2Show.Init();
//            }
//        });
                view2Show.setVisible(true);
                view2Show.Init();
        _currentView=view2Show;
    }

    public void addPazienteSelectedListener(IPazienteSelectedListner c){
        this._pazienteSelectedListeners.add(c);
    }
    public void removePazienteSelectedListener(IPazienteSelectedListner c){
        if(this._pazienteSelectedListeners.contains(c)){
            this._pazienteSelectedListeners.remove(this._pazienteSelectedListeners.indexOf(c));
        }
    }

    public void fireEvent(Paziente paziente){
        Iterator i= this._pazienteSelectedListeners.iterator();
        while(i.hasNext()) {
            ((IPazienteSelectedListner)i.next()).PazienteSelected(paziente);
        }
    }

    public void addConsultoSelectedListener(IConsultoSelectedListner c){
        this._consultoSelectedListeners.add(c);
    }
    public void removeConsultoSelectedListener(IConsultoSelectedListner c){
        if(this._consultoSelectedListeners.contains(c)){
            this._consultoSelectedListeners.remove(this._consultoSelectedListeners.indexOf(c));
        }
    }

    public void fireEvent(Consulto consulto){
        Iterator i= this._consultoSelectedListeners.iterator();
        while(i.hasNext()) {
            ((IConsultoSelectedListner)i.next()).ConsultoSelected(consulto);
        }
    }
}

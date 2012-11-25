/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.UI;


import josteo.infrastructure.UI.ViewBase;
import ca.odell.glazedlists.swing.*;
import ca.odell.glazedlists.*;
import ca.odell.glazedlists.gui.*;
import java.beans.PropertyChangeEvent;

import javax.swing.event.*;
import javax.swing.*;

/**
 *
 * @author cristiano
 */
public abstract class ViewListBase<TItem extends josteo.infrastructure.DomainBase.IEntity> extends ViewBase {
    protected ViewListBase(){
        super();
    }

    protected josteo.infrastructure.UI.ListPresenter<TItem> _presenter;
    protected SortedList _items;

    protected abstract boolean bindToPresenter();
    protected abstract void bindUI();
    protected abstract JTable getTable();

    protected void store(){
        if(this.bindToPresenter()){
            this._presenter.Store();
            if(this._presenter.getMessages().size()>0)
                JOptionPane.showMessageDialog(rootPane, this._presenter.MessagesToString("\n"));
            else{
                this._presenter.Load();
            }
        }
        this._isDirty = false;
    }

    protected void reset(){
        boolean bReset = true;
        if(this._isDirty){
            int i = JOptionPane.showConfirmDialog(null,"Vuoi resettare i dati nel form? Tutti i cambiamneti andranno persi","Dati non salvati", JOptionPane.YES_NO_OPTION);
            bReset = (i==0);
        }
        if(bReset){
            this._isDirty = false;
            this._presenter.setSelected(null);
            //ListSelectionModel model = this.jTable1.getSelectionModel();
            //model.removeSelectionInterval(0,0); //removes the selection of the first line
            this.getTable().getSelectionModel().clearSelection();
            this.bindUI();
        }
    }

    protected void jTable1_SelectionChanged(ListSelectionEvent evt){

      if (evt.getValueIsAdjusting()) return;
      TItem item = null;
      try{
        //System.out.println("Selected from " + evt.getFirstIndex() + " to " + evt.getLastIndex());
        //ListSelectionModel lsm = (ListSelectionModel)evt.getSource();
        if(this._items.size()>0){
            item = (TItem)this._items.get(this.getTable().getSelectedRow());
            System.out.println("selItem:"+item.get_Key().toString());
            this._presenter.setSelected(item);
            bindUI();
        }

      }catch(Exception exc){
        System.out.println(exc.getMessage());
      }finally{

      }
    }

    protected <T> void gridConfig(WritableTableFormat<T> tableFormat){
        //final SortedList<PazientePresenter> pazienti = new SortedList( this._presenter.get_Pazienti(), new NameComparator());

        //TextFilterList filteredCustomers = new TextFilterList(pazienti);
        //this.txtCognome = filteredCustomers.getFilterEdit();
        //filteredCustomers.setFilterEdit(this.jTextField1);

        //PazienteAdvancedTableFormat tableFormat = new PazienteAdvancedTableFormat();
        //EventTableModel tableModel = new EventTableModel(filteredCustomers, tableFormat);
        //EventTableModel tableModel = new EventTableModel(filteredCustomers, tableFormat);
        EventTableModel tableModel = new EventTableModel(_items, tableFormat);
        EventSelectionModel selectionModel = new EventSelectionModel(_items);

        this.getTable().setModel(tableModel);
        this.getTable().setSelectionModel(selectionModel);
    }

    protected class UpdateListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            _isDirty=true;
            System.out.println("descr changed");
        }
        public void removeUpdate(DocumentEvent e) {
            _isDirty=true;
        }

        public void changedUpdate(DocumentEvent e) {
            _isDirty=true;
            System.out.println("descr changed");
        }
    }

    protected class MyPropertyChangeListener implements java.beans.PropertyChangeListener {
      public void propertyChange(PropertyChangeEvent evt) {
        Object oldValue = evt.getOldValue();
        Object newValue = evt.getNewValue();
        _isDirty =  newValue==null || (newValue instanceof java.util.Date );
        System.out.printf("date changed %s -> %s\n", oldValue, newValue);
      }
    }

}

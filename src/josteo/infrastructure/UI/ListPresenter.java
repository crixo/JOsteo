/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.UI;

import java.util.*;
import josteo.model.paziente.*;
import josteo.application.*;

import ca.odell.glazedlists.*;

/**
 *
 * @author cristiano
 */
public abstract class ListPresenter<TItem> extends PresenterBase{
    protected EventList<TItem> Items;
    protected TItem SelectedItem;
    protected TItem NewItem;

    public EventList<TItem> getItems(){ return this.Items;}

    public TItem getNewItem(){return this.NewItem;}
    
    public TItem getSelected(){return this.SelectedItem;}
    public void setSelected(TItem val){
        //if(this.SelectedItem!=(val)) return;
        this.SelectedItem=val;
    }


    public ListPresenter(){
        super();
        this.Items = new BasicEventList<TItem>();
    }

    public abstract void Load();
    public abstract void Add();
    public abstract void Update();
    
    public void Delete(){
        if(this.getSelected()!=null){
            this.getItems().remove(this.getSelected());
            this.setSelected(null);
        }
    }

    public void Store(){
        this.Messages.clear();
        if(this.getSelected()!=null)
            this.Update();
        else
            this.Add();
    }

}

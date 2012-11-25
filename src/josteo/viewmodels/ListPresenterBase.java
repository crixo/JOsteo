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
public abstract class ListPresenterBase<TEntity extends EntityBase> extends josteo.infrastructure.UI.ListPresenter<EntityBase>{
    protected IPazienteService Svc;
    private Class<TEntity> typeofT;

    protected ListPresenterBase(){
        super();

        createEmptyItem();

        this.Svc = (IPazienteService) ContainerHelper.getContainer().getBean("PazienteService");
    }
    
//    private void createEmptyItem(){
//        try{
//            //Object cs = typeofT.getConstructors();
//            //TEntity o = typeofT.getConstructor(new Class[] {}).newInstance(new Object[] {});
//            this.NewItem = typeofT.newInstance();
//        }catch(Exception exc){
//            System.out.println(exc.getMessage());
//        }
//    }

    protected abstract void createEmptyItem();
    protected abstract List<TEntity> GetList();

    @Override
    public TEntity getSelected(){return (TEntity)this.SelectedItem;}

    @Override
    public TEntity getNewItem(){return (TEntity)this.NewItem;}

    @Override
    public void Load(){
        this.Items.clear();
        for( TEntity e : this.GetList() )
            this.Items.add(e);
    }

    @Override
    public void Add(){
        List<BrokenRule> brokenRules = this.NewItem.GetBrokenRules();
        if(brokenRules.size()>0){
            for(BrokenRule br : brokenRules)
                this.Messages.add(br.get_Message());
        }else{
            this.GetList().add((TEntity)this.NewItem);
            this.Svc.SavePaziente(josteo.application.ApplicationState.getInstance().getCurrentPaziente());
            createEmptyItem();
        }

    }

    @Override
    public void Update(){
        List<BrokenRule> brokenRules = this.getSelected().GetBrokenRules();
        if(brokenRules.size()>0){
            for(BrokenRule br : brokenRules)
                this.Messages.add(br.get_Message());
        }else{
            this.Svc.SavePaziente(josteo.application.ApplicationState.getInstance().getCurrentPaziente());
        }

    }

    @Override
    public void Delete(){
        if(this.getSelected()==null) return;
        int key = (Integer)this.getSelected().get_Key();
        this.getSelected().set_Key(-key);
        this.Svc.SavePaziente(josteo.application.ApplicationState.getInstance().getCurrentPaziente());
        super.Delete();
    }
}

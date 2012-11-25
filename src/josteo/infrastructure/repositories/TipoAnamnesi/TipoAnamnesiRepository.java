/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.TipoAnamnesi;

import java.sql.*;
import java.util.*;

import josteo.infrastructure.helpers.*;
import josteo.model.tipoAnamnesi.*;

/**
 *
 * @author cristiano
 */
public class TipoAnamnesiRepository extends josteo.infrastructure.repositories.MySqlRepositoryBase<TipoAnamnesi> implements ITipoAnamnesiRepository {
    
    public TipoAnamnesiRepository(){
        super();

        this.EntityFactory = new TipoAnamnesiFactory();
    }

    @Override
    public TipoAnamnesi FindBy(Object key){
        StringBuilder sb = this.GetBaseQueryBuilder();
        sb.append("WHERE LCV=0 AND ID=");
        sb.append(key);

        TipoAnamnesi entity = null;
        
        ResultSet rs;

        try
        {
            rs = this.ExecuteQuery(sb.toString());
            if(rs.first()){
                entity = this.EntityFactory.BuildEntity(rs);
            } else
               ConfigHelper.getInstance().WriteLog("Not TipoAnamnesi found by ID="+key.toString());
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
        return entity;
    }

    @Override
    public List<TipoAnamnesi> FindAll(){
        List<TipoAnamnesi> list = new ArrayList<TipoAnamnesi>();
        try
        {
            StringBuilder sb = this.GetBaseQueryBuilder();
            sb.append("WHERE LCV=0");
            ResultSet rs = this.ExecuteQuery(sb.toString());
            while(rs.next()){
                list.add(this.EntityFactory.BuildEntity(rs));
            }
        }
        catch(Exception exc)
        {
            ConfigHelper.getInstance().WriteLog(exc.getMessage());
        }
        return list;
    }

    @Override
    protected StringBuilder GetBaseQueryBuilder(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM lkp_anamnesi ");
        return sb;
    }


    //#region Unit of Work Implementation
    @Override
    protected void PersistNewItem(TipoAnamnesi item){
        crud(item, false);
    }

    @Override
    protected void PersistUpdatedItem(TipoAnamnesi item){
        crud(item, false);
    }

    @Override
    protected void PersistDeletedItem(TipoAnamnesi item){
        crud(item, true);
    }
    //#endregion

    private void crud(TipoAnamnesi item, boolean bDelete){
        TipoAnamnesiCRUDBuilder crudB = new TipoAnamnesiCRUDBuilder(this.database);
        try
        {
            this.DoCrud(item, crudB, null);
        }
        catch(Exception exc)
        {
            ConfigHelper.getInstance().WriteLog(exc.getMessage());
        }
    }
    
}

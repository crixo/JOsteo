/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.TipoEsame;

import java.sql.*;
import java.util.*;

import josteo.infrastructure.helpers.*;
import josteo.model.tipoEsame.*;

/**
 *
 * @author cristiano
 */
public class TipoEsameRepository extends josteo.infrastructure.repositories.MySqlRepositoryBase<TipoEsame> implements ITipoEsameRepository {
    
    public TipoEsameRepository(){
        super();

        this.EntityFactory = new TipoEsameFactory();
    }

    @Override
    public TipoEsame FindBy(Object key){
        StringBuilder sb = this.GetBaseQueryBuilder();
        sb.append("WHERE LCV=0 AND ID=");
        sb.append(key);

        TipoEsame entity = null;
        
        ResultSet rs;

        try
        {
            rs = this.ExecuteQuery(sb.toString());
            if(rs.first()){
                entity = this.EntityFactory.BuildEntity(rs);
            } else
               ConfigHelper.getInstance().WriteLog("Not TipoEsame found by ID="+key.toString());
        }
        catch(Exception exc)
        {
            ConfigHelper.getInstance().WriteLog(exc.getMessage());
        }
        return entity;
    }

    @Override
    public List<TipoEsame> FindAll(){
        List<TipoEsame> list = new ArrayList<TipoEsame>();
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
        sb.append("SELECT * FROM lkp_esame ");
        return sb;
    }


    //#region Unit of Work Implementation
    @Override
    protected void PersistNewItem(TipoEsame item){
        crud(item, false);
    }

    @Override
    protected void PersistUpdatedItem(TipoEsame item){
        crud(item, false);
    }

    @Override
    protected void PersistDeletedItem(TipoEsame item){
        crud(item, true);
    }
    //#endregion
    private void crud(TipoEsame item, boolean bDelete){
        TipoEsameCRUDBuilder crudB = new TipoEsameCRUDBuilder(this.database);
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

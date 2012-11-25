/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.TipoEsame;

import josteo.infrastructure.EntityFactoryFramework.*;
import java.sql.ResultSet;
import josteo.model.tipoEsame.TipoEsame;


/**
 *
 * @author cristiano
 */
public class TipoEsameFactory implements IEntityFactory<TipoEsame>{
    public TipoEsame BuildEntity(Object recordset){
        ResultSet rs = (ResultSet) recordset;
        int key = 0;
        TipoEsame entity = null;
        try
        {
            key = rs.getInt("ID");
            entity = new TipoEsame(key, rs.getString("descrizione"));
        }
        catch(Exception exc)
        {
            josteo.infrastructure.helpers.ConfigHelper.getInstance().WriteLog(exc.getMessage());
        }


        return entity;
    }
}

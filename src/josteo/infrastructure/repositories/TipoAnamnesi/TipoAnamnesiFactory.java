/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.TipoAnamnesi;

import josteo.infrastructure.repositories.TipoAnamnesi.*;
import josteo.infrastructure.EntityFactoryFramework.*;
import java.sql.ResultSet;
import josteo.model.tipoAnamnesi.TipoAnamnesi;


/**
 *
 * @author cristiano
 */
public class TipoAnamnesiFactory implements IEntityFactory<TipoAnamnesi>{
    public TipoAnamnesi BuildEntity(Object recordset){
        ResultSet rs = (ResultSet) recordset;
        int key = 0;
        TipoAnamnesi entity = null;
        try
        {
            key = rs.getInt("ID");
            entity = new TipoAnamnesi(key, rs.getString("descrizione"));
        }
        catch(Exception exc)
        {
            josteo.infrastructure.helpers.ConfigHelper.getInstance().WriteLog(exc.getMessage());
        }


        return entity;
    }
}

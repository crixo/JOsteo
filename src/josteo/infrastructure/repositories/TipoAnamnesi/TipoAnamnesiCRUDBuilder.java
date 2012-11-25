/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.TipoAnamnesi;


import josteo.infrastructure.helpers.ConfigHelper;
import josteo.infrastructure.EntityFactoryFramework.IEntityCRUDBuilder;
import josteo.model.tipoAnamnesi.TipoAnamnesi;
import java.sql.*;
import josteo.infrastructure.repositories.MySqlCRUDBuilderBase;

/**
 *
 * @author cristiano
 */
public class TipoAnamnesiCRUDBuilder extends MySqlCRUDBuilderBase<TipoAnamnesi> implements IEntityCRUDBuilder<TipoAnamnesi,PreparedStatement>{

    public TipoAnamnesiCRUDBuilder(java.sql.Connection conn){
        super(conn);
        this.TableName = "lkp_anamnesi";
    }

    public PreparedStatement CreateSqlInsert(Object[] FKs, TipoAnamnesi entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO lkp_anamnesi ");
        sb.append("( descrizione ) ");
        sb.append("VALUES ( ? )");

        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getDescription());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    public PreparedStatement CreateSqlUpdate(TipoAnamnesi entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE lkp_anamnesi ");
        sb.append("SET ");
        sb.append("descrizione = ? ");
        sb.append("WHERE ID=?");

        try{
            stm = this.Database.prepareStatement(sb.toString());
            stm.setString(1, entity.getDescription());
            stm.setInt(2, (Integer)entity.get_Key());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    @Override
    public PreparedStatement CreateSqlDelete(TipoAnamnesi entity){
        return super.CreateSqlDelete(entity);
    }
}

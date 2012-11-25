/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.TipoEsame;


import josteo.infrastructure.EntityFactoryFramework.IEntityCRUDBuilder;
import josteo.model.tipoEsame.TipoEsame;
import josteo.infrastructure.helpers.ConfigHelper;
import java.sql.*;
import josteo.infrastructure.repositories.MySqlCRUDBuilderBase;

/**
 *
 * @author cristiano
 */
public class TipoEsameCRUDBuilder extends MySqlCRUDBuilderBase<TipoEsame> implements IEntityCRUDBuilder<TipoEsame,PreparedStatement>{

    public TipoEsameCRUDBuilder(java.sql.Connection conn){
        super(conn);
        this.TableName = "lkp_esame";
    }

    public PreparedStatement CreateSqlInsert(Object[] FKs, TipoEsame entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO lkp_esame ");
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

    public PreparedStatement CreateSqlUpdate(TipoEsame entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE lkp_esame ");
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
    public PreparedStatement CreateSqlDelete(TipoEsame entity){
        return super.CreateSqlDelete(entity);
    }
}

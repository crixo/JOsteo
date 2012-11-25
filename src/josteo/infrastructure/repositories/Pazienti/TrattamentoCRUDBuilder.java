/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import josteo.infrastructure.DomainBase.IEntity;
import josteo.infrastructure.EntityFactoryFramework.IEntityCRUDBuilder;
import josteo.model.paziente.Trattamento;
import josteo.infrastructure.helpers.*;
import java.sql.*;
import josteo.infrastructure.repositories.MySqlCRUDBuilderBase;

/**
 *
 * @author cristiano
 */
public class TrattamentoCRUDBuilder extends MySqlCRUDBuilderBase<Trattamento> implements IEntityCRUDBuilder<Trattamento,PreparedStatement>{

    public TrattamentoCRUDBuilder(java.sql.Connection conn){
        super(conn);
        this.TableName = "trattamento";
    }

    public PreparedStatement CreateSqlInsert(Object[] FKs, Trattamento entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO trattamento ");
        sb.append("( ID_consulto,data,descrizione) ");
        sb.append("VALUES ( ?, ?, ?)");

        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, (Integer)FKs[0]);
            stm.setDate(2, this.convertToSqlDate(entity.get_Note().get_Data()));//
            stm.setString(3, entity.get_Note().get_Descrizione());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    public PreparedStatement CreateSqlUpdate(Trattamento entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE trattamento ");
        sb.append("SET ");
        sb.append("data = ?");
        sb.append(",descrizione = ? ");
        sb.append("WHERE ID=?");

        try{
            stm = this.Database.prepareStatement(sb.toString());
            stm.setDate(1, this.convertToSqlDate(entity.get_Note().get_Data()));
            stm.setString(2, entity.get_Note().get_Descrizione());
            stm.setInt(3, (Integer)entity.get_Key());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    @Override
    public PreparedStatement CreateSqlDelete(Trattamento entity){
        return super.CreateSqlDelete(entity);
    }
}

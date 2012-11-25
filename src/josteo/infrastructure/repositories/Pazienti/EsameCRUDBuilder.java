/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import josteo.infrastructure.DomainBase.IEntity;
import josteo.infrastructure.EntityFactoryFramework.IEntityCRUDBuilder;
import josteo.model.paziente.Esame;
import josteo.infrastructure.helpers.*;
import java.sql.*;
import josteo.infrastructure.repositories.MySqlCRUDBuilderBase;

/**
 *
 * @author cristiano
 */
public class EsameCRUDBuilder extends MySqlCRUDBuilderBase<Esame> implements IEntityCRUDBuilder<Esame,PreparedStatement>{

    public EsameCRUDBuilder(java.sql.Connection conn){
        super(conn);
        this.TableName = "esame";
    }

    public PreparedStatement CreateSqlInsert(Object[] FKs, Esame entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO esame ");
        sb.append("( ID_consulto,tipo,data,descrizione) ");
        sb.append("VALUES ( ?, ?, ? ,? )");

        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, (Integer)FKs[0]);
            stm.setInt(2, (Integer)entity.get_TipoEsame().get_Key());
            stm.setDate(3, this.convertToSqlDate(entity.get_Note().get_Data()));//
            stm.setString(4, entity.get_Note().get_Descrizione());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    public PreparedStatement CreateSqlUpdate(Esame entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE esame ");
        sb.append("SET ");
        sb.append("tipo = ?, data = ?, descrizione = ? ");
        sb.append("WHERE ID=?");

        try{
            stm = this.Database.prepareStatement(sb.toString());
            stm.setInt(1, (Integer)entity.get_TipoEsame().get_Key());
            stm.setDate(2, this.convertToSqlDate(entity.get_Note().get_Data()));
            stm.setString(3, entity.get_Note().get_Descrizione());
            stm.setInt(4, (Integer)entity.get_Key());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    @Override
    public PreparedStatement CreateSqlDelete(Esame entity){
        return super.CreateSqlDelete(entity);
    }
}

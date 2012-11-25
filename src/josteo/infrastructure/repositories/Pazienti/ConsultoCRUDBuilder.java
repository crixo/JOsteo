/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import josteo.infrastructure.DomainBase.IEntity;
import josteo.infrastructure.EntityFactoryFramework.IEntityCRUDBuilder;
import josteo.model.paziente.Consulto;
import josteo.infrastructure.helpers.*;
import java.util.*;
import java.sql.*;
import josteo.infrastructure.repositories.MySqlCRUDBuilderBase;

/**
 *
 * @author cristiano
 */
public class ConsultoCRUDBuilder extends MySqlCRUDBuilderBase<Consulto> implements IEntityCRUDBuilder<Consulto,PreparedStatement>{
    
    public ConsultoCRUDBuilder(java.sql.Connection conn){
        super(conn);
        this.TableName = "consulto";
    }

    public PreparedStatement CreateSqlInsert(Object[] FKs, Consulto entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO consulto ");
        sb.append("( ID_paziente,data,problema_iniziale) ");
        sb.append("VALUES ( ?, ?, ? )");

        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, (Integer)FKs[0]);
            stm.setDate(2, this.convertToSqlDate(entity.get_Data()));//
            stm.setString(3, entity.get_ProblemaIniziale());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    public PreparedStatement CreateSqlUpdate(Consulto entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE consulto ");
        sb.append("SET ");
        sb.append("data = ?, problema_iniziale = ? ");
        sb.append("WHERE ID=?");

        try{
            stm = this.Database.prepareStatement(sb.toString());
            stm.setDate(1, this.convertToSqlDate(entity.get_Data()));
            stm.setString(2, entity.get_ProblemaIniziale());
            stm.setInt(3, (Integer)entity.get_Key());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    @Override
    public PreparedStatement CreateSqlDelete(Consulto entity){
        return super.CreateSqlDelete(entity);
    }
}

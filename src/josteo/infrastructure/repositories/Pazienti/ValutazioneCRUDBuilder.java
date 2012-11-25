/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import josteo.infrastructure.DomainBase.IEntity;
import josteo.infrastructure.EntityFactoryFramework.IEntityCRUDBuilder;
import josteo.model.paziente.Valutazione;
import josteo.infrastructure.helpers.*;
import java.sql.*;
import josteo.infrastructure.repositories.MySqlCRUDBuilderBase;

/**
 *
 * @author cristiano
 */
public class ValutazioneCRUDBuilder extends MySqlCRUDBuilderBase<Valutazione> implements IEntityCRUDBuilder<Valutazione,PreparedStatement>{

    public ValutazioneCRUDBuilder(java.sql.Connection conn){
        super(conn);
        this.TableName = "valutazione";
    }

    public PreparedStatement CreateSqlInsert(Object[] FKs, Valutazione entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO "+this.TableName+" ");
        sb.append("( ID_consulto,strutturale,cranio_sacrale,ak_ortodontica) ");
        sb.append("VALUES ( ?, ?, ?, ?)");

        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, (Integer)FKs[0]);
            stm.setString(2, entity.get_Strutturale());//
            stm.setString(3, entity.get_CranioSacrale());
            stm.setString(4, entity.get_AkOrtodontica());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    public PreparedStatement CreateSqlUpdate(Valutazione entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE "+this.TableName+" ");
        sb.append("SET ");
        sb.append("strutturale=? ");
        sb.append(",cranio_sacrale=? ");
        sb.append(",ak_ortodontica=? ");
        sb.append("WHERE ID=?");

        try{
            stm = this.Database.prepareStatement(sb.toString());
            stm.setString(1, entity.get_Strutturale());
            stm.setString(2, entity.get_CranioSacrale());
            stm.setString(3, entity.get_AkOrtodontica());
            stm.setInt(4, (Integer)entity.get_Key());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    @Override
    public PreparedStatement CreateSqlDelete(Valutazione entity){
        return super.CreateSqlDelete(entity);
    }
}

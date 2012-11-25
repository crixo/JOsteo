/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import java.sql.*;
import java.util.*;
import josteo.infrastructure.DomainBase.IEntity;
import josteo.infrastructure.EntityFactoryFramework.IEntityCRUDBuilder;
import josteo.model.paziente.AnamnesiRemota;
import josteo.infrastructure.helpers.*;
import josteo.infrastructure.repositories.MySqlCRUDBuilderBase;

/**
 *
 * @author cristiano
 */
public class AnamnesiRemotaCRUDBuilder extends MySqlCRUDBuilderBase<AnamnesiRemota> implements IEntityCRUDBuilder<AnamnesiRemota, PreparedStatement>{

    public AnamnesiRemotaCRUDBuilder(java.sql.Connection conn){
        super(conn);
        this.TableName = "anamnesi_remota";
    }

    public PreparedStatement CreateSqlInsert(Object[] FKs, AnamnesiRemota entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO anamnesi_remota ");
        sb.append("( ID_paziente,tipo,data,descrizione) ");
        sb.append("VALUES ( ?, ?, ? ,? )");

        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, (Integer)FKs[0]);
            stm.setInt(2, (Integer)entity.get_Tipo().get_Key());
            stm.setDate(3, this.convertToSqlDate(entity.get_Note().get_Data()));//
            stm.setString(4, entity.get_Note().get_Descrizione());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    public PreparedStatement CreateSqlUpdate(AnamnesiRemota entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE anamnesi_remota ");
        sb.append("SET ");
        sb.append("tipo = ?, data = ?, descrizione = ? ");
        sb.append("WHERE ID=?");

        try{
            stm = this.Database.prepareStatement(sb.toString());
            stm.setInt(1, (Integer)entity.get_Tipo().get_Key());
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
    public PreparedStatement CreateSqlDelete(AnamnesiRemota entity){
        return super.CreateSqlDelete(entity);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import java.sql.*;
import josteo.infrastructure.DomainBase.IEntity;
import josteo.infrastructure.EntityFactoryFramework.IEntityCRUDBuilder;
import josteo.model.paziente.AnamnesiProssima;
import josteo.infrastructure.helpers.*;
import josteo.infrastructure.repositories.MySqlCRUDBuilderBase;

/**
 *
 * @author cristiano
 */
public class AnamnesiProssimaCRUDBuilder extends MySqlCRUDBuilderBase<AnamnesiProssima> implements IEntityCRUDBuilder<AnamnesiProssima, PreparedStatement>{

    public AnamnesiProssimaCRUDBuilder(java.sql.Connection conn){
        super(conn);
        this.TableName = "anamnesi_prossima";
    }

    public PreparedStatement CreateSqlInsert(Object[] FKs, AnamnesiProssima entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO anamnesi_prossima ");
        sb.append("( ID_consulto,prima_volta,tipologia,localizzazione,irradiazione,periodo_insorgenza,durata,familiarita,altre_terapie,varie) ");
        sb.append("VALUES ( ?,?,?,?,?,?,?,?,?,? ) ");

        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, (Integer)FKs[0]);
            stm.setString(2, entity.get_PrimaVolta());
            stm.setString(3, entity.get_Tipologia());
            stm.setString(4, entity.get_Localizzazione());
            stm.setString(5, entity.get_Irradiazione());
            stm.setString(6, entity.get_PeriodoInsorgenza());
            stm.setString(7, entity.get_Durata());
            stm.setString(8, entity.get_Familiarita());
            stm.setString(9, entity.get_AltreTerapie());
            stm.setString(10, entity.get_Varie());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    public PreparedStatement CreateSqlUpdate(AnamnesiProssima entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE anamnesi_prossima ");
        sb.append("SET ");
        sb.append("prima_volta=?");
        sb.append(",tipologia=?");
        sb.append(",localizzazione=?");
        sb.append(",irradiazione=?");
        sb.append(",periodo_insorgenza=?");
        sb.append(",durata=?");
        sb.append(",familiarita=?");
        sb.append(",altre_terapie=?");
        sb.append(",varie=?");
        sb.append(" WHERE ID=?");

        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.get_PrimaVolta());
            stm.setString(2, entity.get_Tipologia());
            stm.setString(3, entity.get_Localizzazione());
            stm.setString(4, entity.get_Irradiazione());
            stm.setString(5, entity.get_PeriodoInsorgenza());
            stm.setString(6, entity.get_Durata());
            stm.setString(7, entity.get_Familiarita());
            stm.setString(8, entity.get_AltreTerapie());
            stm.setString(9, entity.get_Varie());
            stm.setInt(10, (Integer)entity.get_Key());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    @Override
    public PreparedStatement CreateSqlDelete(AnamnesiProssima entity){
        return super.CreateSqlDelete(entity);
    }
}

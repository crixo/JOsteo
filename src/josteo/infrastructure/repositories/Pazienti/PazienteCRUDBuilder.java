/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import josteo.infrastructure.EntityFactoryFramework.IEntityCRUDBuilder;
import josteo.model.paziente.Paziente;
import josteo.infrastructure.helpers.*;
import java.sql.*;
import josteo.infrastructure.repositories.MySqlCRUDBuilderBase;

/**
 *
 * @author cristiano
 */
public class PazienteCRUDBuilder extends MySqlCRUDBuilderBase<Paziente> implements IEntityCRUDBuilder<Paziente,PreparedStatement>{

    public PazienteCRUDBuilder(java.sql.Connection conn){
        super(conn);
        this.TableName = "paziente";
    }

    public PreparedStatement CreateSqlInsert(Object[] FKs, Paziente entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO paziente ");
        sb.append("( nome, cognome, data_nascita, professione, indirizzo, citta, cap, prov, telefono, cellulare, email) ");
        sb.append("VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getNome());
            stm.setString(2, entity.getCognome());
            stm.setDate(3, this.convertToSqlDate(entity.getDataNascita()));//
            stm.setString(4, entity.getProfessione());
            stm.setString(5, entity.getIndirizzo());
            stm.setString(6, entity.getCitta());
            stm.setString(7, entity.getCap());
            stm.setString(8, entity.getProv());
            stm.setString(9, entity.getTelefono());
            stm.setString(10, entity.getCellulare());
            stm.setString(11, entity.getEmail());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }
        return stm;
    }

    public PreparedStatement CreateSqlUpdate(Paziente entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE paziente ");
        sb.append("SET ");
        sb.append("nome = ?");
        sb.append(",cognome = ?");
        sb.append(",data_nascita = ?");
        sb.append(",professione = ?");
        sb.append(",indirizzo = ?");
        sb.append(",citta = ?");
        sb.append(",cap = ?");
        sb.append(",prov = ?");
        sb.append(",telefono = ?");
        sb.append(",cellulare = ?");
        sb.append(",email = ? ");
        sb.append("WHERE ID=?");
        try{
            stm = this.Database.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getNome());
            stm.setString(2, entity.getCognome());
            stm.setDate(3, this.convertToSqlDate(entity.getDataNascita()));//
            stm.setString(4, entity.getProfessione());
            stm.setString(5, entity.getIndirizzo());
            stm.setString(6, entity.getCitta());
            stm.setString(7, entity.getCap());
            stm.setString(8, entity.getProv());
            stm.setString(9, entity.getTelefono());
            stm.setString(10, entity.getCellulare());
            stm.setString(11, entity.getEmail());
            stm.setInt(12, (Integer)entity.get_Key());
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }
        return stm;
    }

    @Override
    public PreparedStatement CreateSqlDelete(Paziente entity){
        return super.CreateSqlLogicalCancel(entity);
    }

}

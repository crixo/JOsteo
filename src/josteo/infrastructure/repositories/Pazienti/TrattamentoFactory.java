/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import josteo.model.paziente.*;
import josteo.infrastructure.EntityFactoryFramework.*;
import josteo.infrastructure.DomainBase.*;
import java.sql.ResultSet;

/**
 *
 * @author cristiano
 */
public class TrattamentoFactory implements IEntityFactory<Trattamento>{
    public Trattamento BuildEntity(Object recordset){
        ResultSet rs = (ResultSet) recordset;
        int key = 0;
        Trattamento entity = null;
        Note note;
        try
        {
            key = rs.getInt("ID");
            note = new Note(rs.getString("descrizione"), rs.getDate("data"));
            entity = new Trattamento(key, note);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }


        return entity;
    }
}

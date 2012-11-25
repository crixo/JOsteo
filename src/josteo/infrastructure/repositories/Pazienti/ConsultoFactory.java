/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import josteo.model.paziente.*;
import josteo.infrastructure.EntityFactoryFramework.*;
import java.sql.ResultSet;

/**
 *
 * @author cristiano
 */
public class ConsultoFactory implements IEntityFactory<Consulto>{
    public Consulto BuildEntity(Object recordset){
        ResultSet rs = (ResultSet) recordset;
        int key = 0;
        Consulto entity = null;
        try
        {
            key = rs.getInt("ID");

            entity = new Consulto(key, rs.getDate("data"), rs.getString("problema_iniziale"));
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }


        return entity;
    }
}

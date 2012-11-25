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
public class AnamnesiProssimaFactory implements IEntityFactory<AnamnesiProssima>{
    public AnamnesiProssima BuildEntity(Object recordset){
        ResultSet rs = (ResultSet) recordset;
        int key = 0;
        AnamnesiProssima entity = null;
        try
        {
            key = rs.getInt("ID");

            entity = new AnamnesiProssima(key
                    , rs.getString("prima_volta")
                    , rs.getString("tipologia")
                    , rs.getString("localizzazione")
                    , rs.getString("irradiazione")
                    , rs.getString("periodo_insorgenza")
                    , rs.getString("durata")
                    , rs.getString("familiarita")
                    , rs.getString("altre_terapie")
                    , rs.getString("varie")
                    );
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }


        return entity;
    }
}

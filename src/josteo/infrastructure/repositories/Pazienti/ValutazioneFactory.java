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
public class ValutazioneFactory implements IEntityFactory<Valutazione>{
    public Valutazione BuildEntity(Object recordset){
        ResultSet rs = (ResultSet) recordset;
        int key = 0;
        Valutazione entity = null;
        try
        {
            key = rs.getInt("ID");

            entity = new Valutazione(key
                    , rs.getString("strutturale")
                    , rs.getString("cranio_sacrale")
                    , rs.getString("ak_ortodontica")
                    );
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }


        return entity;
    }
}

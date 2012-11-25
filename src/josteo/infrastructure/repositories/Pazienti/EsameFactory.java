/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import josteo.model.paziente.*;
import josteo.infrastructure.EntityFactoryFramework.*;
import josteo.infrastructure.DomainBase.*;
import java.sql.ResultSet;
import josteo.model.tipoEsame.TipoEsame;

/**
 *
 * @author cristiano
 */
public class EsameFactory implements IEntityFactory<Esame>{
    public Esame BuildEntity(Object recordset){
        ResultSet rs = (ResultSet) recordset;
        int key = 0;
        Esame entity = null;
        Note note;
        TipoEsame tipo;
        try
        {
            key = rs.getInt("ID");
            note = new Note(rs.getString("descrizione"), rs.getDate("data"));
            tipo = new TipoEsame(rs.getInt("esame_id"),rs.getString("esame_descr"));
            entity = new Esame(key, note, tipo);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }


        return entity;
    }
}

  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import java.sql.ResultSet;
import josteo.model.paziente.*;
import josteo.infrastructure.EntityFactoryFramework.*;
import josteo.infrastructure.DomainBase.*;
import josteo.model.tipoAnamnesi.TipoAnamnesi;
/**
 *
 * @author cristiano
 */
public class AnamnesiRemotaFactory implements IEntityFactory<AnamnesiRemota>{
    public AnamnesiRemota BuildEntity(Object recordset){
        ResultSet rs = (ResultSet) recordset;
        int key = 0;
        AnamnesiRemota entity = null;
        Note note;
        TipoAnamnesi tipo;
        try
        {
            key = rs.getInt("ID");
            note = new Note(rs.getString("descrizione"), rs.getDate("data"));
            tipo = new TipoAnamnesi(rs.getInt("anamnesi_id"),rs.getString("anamnesi_descr"));

            entity = new AnamnesiRemota(key, note, tipo);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }


        return entity;
    }
}

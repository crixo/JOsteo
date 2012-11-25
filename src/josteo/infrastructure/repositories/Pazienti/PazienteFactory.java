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
public class PazienteFactory implements IEntityFactory<Paziente>{
    public Paziente BuildEntity(Object recordset){
        ResultSet rs = (ResultSet) recordset;
        int key = 0;
        Anagrafica anag = null;
        Address addr = null;
        Paziente entity = null;
        try
        { 
            key = rs.getInt("ID");
            
            anag = new Anagrafica(
                    rs.getString("nome")
                    , rs.getString("cognome")
                    , rs.getDate("data_nascita")
                    , rs.getString("professione"));

            addr = new Address(
                    rs.getString("indirizzo")
                    , rs.getString("citta")
                    , rs.getString("telefono")
                    , rs.getString("cellulare")
                    , rs.getString("prov")
                    , rs.getString("cap")
                    , rs.getString("email"));

            entity = new Paziente(key);//, anag, addr
            entity.setNome(rs.getString("nome"));
            entity.setCognome(rs.getString("cognome"));
            entity.setDataNascita(rs.getDate("data_nascita"));
            entity.setProfessione(rs.getString("professione"));
            entity.setIndirizzo(rs.getString("indirizzo"));
            entity.setCitta(rs.getString("citta"));
            entity.setTelefono(rs.getString("telefono"));
            entity.setCellulare(rs.getString("cellulare"));
            entity.setProv(rs.getString("prov"));
            entity.setCap(rs.getString("cap"));
            entity.setEmail(rs.getString("email"));
            entity.set_IsChanged(false);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }

         
        return entity;
    }
}

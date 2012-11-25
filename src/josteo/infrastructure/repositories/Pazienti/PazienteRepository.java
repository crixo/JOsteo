/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories.Pazienti;

import java.sql.*;
import java.util.*;

import josteo.infrastructure.helpers.*;
import josteo.infrastructure.DomainBase.IAggregateRoot;
import josteo.infrastructure.RepositoryFramework.*;
import josteo.model.paziente.*;

/**
 *
 * @author cristiano
 */
public class PazienteRepository extends josteo.infrastructure.repositories.MySqlRepositoryBase<Paziente> implements IPazienteRepository { 
    
    public PazienteRepository(){
        super();

        this.EntityFactory = new PazienteFactory();
    }

    public List<LkpProvincia> GetProvince(){
        List<LkpProvincia> list = new ArrayList<LkpProvincia>();
        ResultSet rs;


        Exception managedExc=null;
        try
        {
            rs = this.ExecuteQuery("SELECT * FROM lkp_provincia");
            while(rs.next())
                list.add(new LkpProvincia(rs.getString("sigla"), rs.getString("descrizione")));
        }
        catch(Exception exc)
        {
            ConfigHelper.getInstance().WriteLog(exc);
            managedExc = exc;
        }
        finally
        {
            if(managedExc!=null)
                throw new RuntimeException(managedExc.getMessage());
        }
        return list;
    }

    @Override
    public Paziente FindBy(Object key){
        StringBuilder sb = this.GetBaseQueryBuilder();
        sb.append("WHERE LCV=0 AND ID=");
        sb.append(key);

        Paziente paziente = null;
        Consulto consulto = null;
        ConsultoFactory consultoFactory = new ConsultoFactory();
        AnamnesiRemota anamnesiRemota = null;
        AnamnesiRemotaFactory anamnesiRemotaFactory = new AnamnesiRemotaFactory();
        AnamnesiProssima anamnesiProssima = null;
        AnamnesiProssimaFactory anamnesiProssimaFactory = new AnamnesiProssimaFactory();
        Trattamento trattamento = null;
        TrattamentoFactory trattamentoFactory = new TrattamentoFactory();
        Valutazione valutazione = null;
        ValutazioneFactory valutazioneFactory = new ValutazioneFactory();
        Esame esame = null;
        EsameFactory esameFactory = new EsameFactory();
        
        ResultSet rsPaziente;
        ResultSet rsAnamnesiRemote;
        ResultSet rsCosulti;
        ResultSet rsAp;
        ResultSet rsValutazione;
        ResultSet rsTrattamento;
        ResultSet rsEsame;
        


        Exception managedExc=null;
        try
        {
            rsPaziente = this.ExecuteQuery(sb.toString());
            if(rsPaziente.first()){
                paziente = this.EntityFactory.BuildEntity(rsPaziente);
                
                sb.setLength(0);
                sb.append("SELECT * FROM consulto ");
                sb.append("WHERE ID_paziente=");
                sb.append(key);

                rsCosulti = this.ExecuteQuery(sb.toString());
                while(rsCosulti.next()){
                   consulto = consultoFactory.BuildEntity(rsCosulti);
                   paziente.get_Consulti().add(consulto);

                    sb.setLength(0);
                    sb.append("SELECT * FROM anamnesi_prossima ");
                    sb.append("WHERE ID_consulto=");
                    sb.append(consulto.get_Key());
                    rsAp = this.ExecuteQuery(sb.toString());
                    while(rsAp.next()){
                        anamnesiProssima = anamnesiProssimaFactory.BuildEntity(rsAp);
                        consulto.get_AnamesiProssime().add(anamnesiProssima);
                    }

                    sb.setLength(0);
                    sb.append("SELECT * FROM trattamento ");
                    sb.append("WHERE ID_consulto=");
                    sb.append(consulto.get_Key());
                    rsTrattamento = this.ExecuteQuery(sb.toString());
                    while(rsTrattamento.next()){
                        trattamento = trattamentoFactory.BuildEntity(rsTrattamento);
                        consulto.get_Trattamenti().add(trattamento);
                    }

                    sb.setLength(0);
                    sb.append("SELECT * FROM valutazione ");
                    sb.append("WHERE ID_consulto=");
                    sb.append(consulto.get_Key());
                    rsAp = this.ExecuteQuery(sb.toString());
                    while(rsAp.next()){
                        valutazione = valutazioneFactory.BuildEntity(rsAp);
                        consulto.get_Valutazioni().add(valutazione);
                    }

                    sb.setLength(0);
                    sb.append("SELECT e.*,lk.ID as esame_id,lk.descrizione as esame_descr ");
                    sb.append("FROM esame e ");
                    sb.append("INNER JOIN lkp_esame lk ON lk.ID=e.tipo ");
                    sb.append("WHERE ID_consulto=");
                    sb.append(consulto.get_Key());
                    rsAp = this.ExecuteQuery(sb.toString());
                    while(rsAp.next()){
                        esame = esameFactory.BuildEntity(rsAp);
                        consulto.get_Esami().add(esame);
                    }

                }

                sb.setLength(0);
                sb.append("SELECT a.*,lk.ID as anamnesi_id,lk.descrizione as anamnesi_descr ");
                sb.append("FROM anamnesi_remota a ");
                sb.append("INNER JOIN lkp_anamnesi lk ON lk.ID=a.tipo ");
                sb.append("WHERE ID_paziente=");
                sb.append(key);

                rsAnamnesiRemote = this.ExecuteQuery(sb.toString());
                while(rsAnamnesiRemote.next()){
                   anamnesiRemota = anamnesiRemotaFactory.BuildEntity(rsAnamnesiRemote);
                   paziente.get_AnamnesiRemote().add(anamnesiRemota);
                }
                
            } else
               System.out.println("Not Paziente found by ID="+key.toString());
        }
        catch(Exception exc)
        {
            ConfigHelper.getInstance().WriteLog(exc);
            managedExc = exc;
        }
        finally
        {
            if(managedExc!=null)
                throw new RuntimeException(managedExc.getMessage());
        }
        return paziente;
    }

    @Override
    public List<Paziente> FindAll(){
        List<Paziente> list = new ArrayList<Paziente>();
        Exception managedExc=null;
        try
        {
            StringBuilder sb = this.GetBaseQueryBuilder();
            sb.append("WHERE LCV=0");
            ResultSet rs = this.ExecuteQuery(sb.toString());
            while(rs.next()){
                list.add(this.EntityFactory.BuildEntity(rs));
            }
        }
        catch(Exception exc)
        {
            ConfigHelper.getInstance().WriteLog(exc);
            managedExc = exc;
        }
        finally
        {
            if(managedExc!=null)
                throw new RuntimeException(managedExc.getMessage());
        }
        return list;
    }

    @Override
    protected StringBuilder GetBaseQueryBuilder(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM paziente ");
        return sb;
    }


    //#region Unit of Work Implementation
    @Override
    protected void PersistNewItem(Paziente item){
        PazienteCRUDBuilder pazienteCrudB = new PazienteCRUDBuilder(this.database);
        Exception managedExc=null;
        try
        {
            this.DoCrud(item, pazienteCrudB, null);
        }
        catch(Exception exc)
        {
            ConfigHelper.getInstance().WriteLog(exc);
            managedExc = exc;
        }
        finally
        {
            if(managedExc!=null)
                throw new RuntimeException(managedExc.getMessage());
        }
    }

    @Override
    protected void PersistUpdatedItem(Paziente item){
        graphUpdateOrDelete(item,false);
    }

    @Override
    protected void PersistDeletedItem(Paziente item){
        PazienteCRUDBuilder pazienteCrudB = new PazienteCRUDBuilder(this.database);Exception managedExc=null;
        try{
            this.DoCrud(item, pazienteCrudB, null);
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            managedExc = exc;
        }finally{
            if(managedExc!=null)
                throw new RuntimeException(managedExc.getMessage());
        }

    }
    //#endregion
    
    private void graphUpdateOrDelete(Paziente item, boolean bDelete){
        PazienteCRUDBuilder pazienteCrudB = new PazienteCRUDBuilder(this.database);
        ConsultoCRUDBuilder consultoCrudB = new ConsultoCRUDBuilder(this.database);
        AnamnesiRemotaCRUDBuilder arCrudB = new AnamnesiRemotaCRUDBuilder(this.database);
        AnamnesiProssimaCRUDBuilder apCrudB = new AnamnesiProssimaCRUDBuilder(this.database);
        TrattamentoCRUDBuilder trattamentoCrudB = new TrattamentoCRUDBuilder(this.database);
        ValutazioneCRUDBuilder valutazioneCrudB = new ValutazioneCRUDBuilder(this.database);
        EsameCRUDBuilder esameCrudB = new EsameCRUDBuilder(this.database);

        Object[] fks1 = new Object[1];
        Object[] fks2 = new Object[1];
        Exception managedExc=null;
        try
        {
            this.DoCrud(item, pazienteCrudB, null);

            fks1[0] = item.get_Key();
            for(Consulto consulto : item.get_Consulti()){
                //Object[] fks = {item.get_Key()};

                this.DoCrud(consulto, consultoCrudB, fks1);

                fks2[0] = consulto.get_Key();
                for(Esame e : consulto.get_Esami())
                   this.DoCrud(e, esameCrudB, fks2);
                for(AnamnesiProssima ap : consulto.get_AnamesiProssime())
                   this.DoCrud(ap, apCrudB, fks2);
                for(Trattamento trattamento : consulto.get_Trattamenti())
                   this.DoCrud(trattamento, trattamentoCrudB, fks2);
                for(Valutazione valutazione : consulto.get_Valutazioni())
                   this.DoCrud(valutazione, valutazioneCrudB, fks2);

            }

            for(AnamnesiRemota ar : item.get_AnamnesiRemote())
                this.DoCrud(ar, arCrudB, fks1);

        }
        catch(Exception exc)
        {
            ConfigHelper.getInstance().WriteLog(exc);
            managedExc = exc;
        }
        finally
        {
            if(managedExc!=null)
                throw new RuntimeException(managedExc.getMessage());
        }
    }

    
}

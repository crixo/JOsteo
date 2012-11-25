/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.application;

import java.util.*;

import josteo.infrastructure.UnitOfWork.*;
import josteo.model.paziente.IPazienteRepository;
import josteo.model.paziente.LkpProvincia;
import josteo.model.paziente.Paziente;

/**
 *
 * @author cristiano
 */
public class PazienteService implements IPazienteService{
    private IPazienteRepository repository;
    private IUnitOfWork unitOfWork;

    public PazienteService(IPazienteRepository repository){
        this.unitOfWork = new UnitOfWork();
        this.repository = repository;//;new josteo.infrastructure.repositories.Pazienti.PazienteRepository();
        this.repository.SetUnitOfWork(this.unitOfWork);
        System.out.println("PazienteService ctor");
    }


    public List<Paziente> GetAllPazienti()
    {
        return this.repository.FindAll();
    }

    public void SavePaziente(Paziente paziente)
    {
        Object key = paziente.get_Key();
        if( key.equals((Object)0) )
            this.repository.Add(paziente);
        else
            this.repository.Update(paziente);
        this.unitOfWork.Commit();
    }

    public Paziente GetPaziente(Object pazienteKey)
    {
        return this.repository.FindBy(pazienteKey);
    }

    public void DeletePaziente(Paziente paziente)
    {
        this.repository.Remove(paziente);
        this.unitOfWork.Commit();
    }

    public List<LkpProvincia> GetAllProvince()
    {
        return this.repository.GetProvince();
    }
}

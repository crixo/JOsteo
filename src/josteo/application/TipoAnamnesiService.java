/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.application;

import java.util.*;

import josteo.infrastructure.UnitOfWork.*;
import josteo.model.tipoAnamnesi.*;

/**
 *
 * @author cristiano
 */
public class TipoAnamnesiService implements ITipoAnamnesiService{
    private ITipoAnamnesiRepository repository;
    private IUnitOfWork unitOfWork;

    public TipoAnamnesiService(){
        this.unitOfWork = new UnitOfWork();
        this.repository = new josteo.infrastructure.repositories.TipoAnamnesi.TipoAnamnesiRepository();
        this.repository.SetUnitOfWork(this.unitOfWork);
    }


    public List<TipoAnamnesi> GetAllAnamnesi()
    {
        return this.repository.FindAll();
    }

    public void SaveTipoAnamnesi(TipoAnamnesi entity)
    {
        Object key = entity.get_Key();
        if( key.equals((Object)0) )
            this.repository.Add(entity);
        else
            this.repository.Update(entity);
        this.unitOfWork.Commit();
    }

    public TipoAnamnesi GetTipoAnamnesi(Object key)
    {
        return this.repository.FindBy(key);
    }

    public void DeleteAnamnesi(TipoAnamnesi entity)
    {
        this.repository.Remove(entity);
        this.unitOfWork.Commit();
    }

}

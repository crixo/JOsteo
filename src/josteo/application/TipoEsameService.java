/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.application;

import java.util.*;

import josteo.infrastructure.UnitOfWork.*;
import josteo.model.tipoEsame.*;
import josteo.model.tipoEsame.ITipoEsameRepository;

/**
 *
 * @author cristiano
 */
public class TipoEsameService implements ITipoEsameService{
    private ITipoEsameRepository repository;
    private IUnitOfWork unitOfWork;

    public TipoEsameService(){
        this.unitOfWork = new UnitOfWork();
        this.repository = new josteo.infrastructure.repositories.TipoEsame.TipoEsameRepository();
        this.repository.SetUnitOfWork(this.unitOfWork);
    }


    public List<TipoEsame> GetAllEsami()
    {
        return this.repository.FindAll();
    }

    public void SaveTipoEsame(TipoEsame entity)
    {
        Object key = entity.get_Key();
        if( key.equals((Object)0) )
            this.repository.Add(entity);
        else
            this.repository.Update(entity);
        this.unitOfWork.Commit();
    }

    public TipoEsame GetTipoEsame(Object key)
    {
        return this.repository.FindBy(key);
    }

    public void DeleteEsame(TipoEsame entity)
    {
        this.repository.Remove(entity);
        this.unitOfWork.Commit();
    }

}

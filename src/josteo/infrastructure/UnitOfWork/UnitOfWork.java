/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.UnitOfWork;

import java.util.*;
import josteo.infrastructure.DomainBase.IEntity;
import josteo.infrastructure.RepositoryFramework.IUnitOfWorkRepository;

/**
 *
 * @author cristiano
 */
public class UnitOfWork implements IUnitOfWork{
    private UUID _key;

    private Map<IEntity, IUnitOfWorkRepository> _addedEntities;
    private Map<IEntity, IUnitOfWorkRepository> _changedEntities;
    private Map<IEntity, IUnitOfWorkRepository> _deletedEntities ;

    public UnitOfWork(){
        this._key = UUID.randomUUID();

        this._addedEntities = new TreeMap<IEntity,IUnitOfWorkRepository>();
        this._changedEntities = new TreeMap<IEntity,IUnitOfWorkRepository>();
        this._deletedEntities = new TreeMap<IEntity,IUnitOfWorkRepository>();
    }


    public void RegisterAdded(IEntity entity, IUnitOfWorkRepository repository){
        this._addedEntities.put(entity, repository);
    }

    public void RegisterChanged(IEntity entity, IUnitOfWorkRepository repository){
        this._changedEntities.put(entity, repository);
    }

    public void RegisterRemoved(IEntity entity, IUnitOfWorkRepository repository){
        this._deletedEntities.put(entity, repository);
    }

    public void Commit(){
        //using (TransactionScope scope = new TransactionScope())
        {
            for (Map.Entry<IEntity,IUnitOfWorkRepository> entry : this._deletedEntities.entrySet()){
                entry.getValue().PersistDeletedItem(entry.getKey());
            }

            for (Map.Entry<IEntity,IUnitOfWorkRepository> entry : this._addedEntities.entrySet()){
                entry.getValue().PersistNewItem(entry.getKey());
            }

            for (Map.Entry<IEntity,IUnitOfWorkRepository> entry : this._changedEntities.entrySet()){
                entry.getValue().PersistUpdatedItem(entry.getKey());
            }

            //scope.Complete();
        }

        this._deletedEntities.clear();
        this._addedEntities.clear();
        this._changedEntities.clear();
        this._key = UUID.randomUUID();
    }

    public UUID get_Key(){
        return this._key;
    }
}

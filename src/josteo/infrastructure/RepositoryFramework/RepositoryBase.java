/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.RepositoryFramework;

import josteo.infrastructure.DomainBase.*;
import josteo.infrastructure.UnitOfWork.IUnitOfWork;
import java.util.*;

/**
 *
 * @author cristiano
 */
public abstract class RepositoryBase<T extends IAggregateRoot> implements IRepository<T>, IUnitOfWorkRepository {

        private IUnitOfWork unitOfWork;

        protected RepositoryBase() {
             this(null);
        }

        protected RepositoryBase(IUnitOfWork unitOfWork){
            this.unitOfWork = unitOfWork;
        }

        public abstract T FindBy(Object key);

        public abstract List<T> FindAll();

        public void SetUnitOfWork(IUnitOfWork unitOfWork){
            this.unitOfWork = unitOfWork;
        }

        public void Add(T item){
            if (this.unitOfWork != null){
                this.unitOfWork.RegisterAdded(item, this);
            }
        }

        public void Update(T item){
            if (this.unitOfWork != null){

                if(this.FindBy(item.get_Key())!=null)
                    this.unitOfWork.RegisterChanged(item, this);
            }
        }

        public void Remove(T item){
            if (this.unitOfWork != null){
                this.unitOfWork.RegisterRemoved(item, this);
            }
        }


        //#endregion

        //#region IUnitOfWorkRepository Members


        public void PersistNewItem(IEntity item)
        {
            this.PersistNewItem((T)item);
        }

        public void PersistUpdatedItem(IEntity item)
        {
            this.PersistUpdatedItem((T)item);
        }

        public void PersistDeletedItem(IEntity item)
        {
            this.PersistDeletedItem((T)item);
        }

        //#endregion


        protected IUnitOfWork get_UnitOfWork(){
            return this.unitOfWork;
        }


        protected abstract void PersistNewItem(T item);
        protected abstract void PersistUpdatedItem(T item);
        protected abstract void PersistDeletedItem(T item);

    }

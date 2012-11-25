/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.UnitOfWork;

import josteo.infrastructure.DomainBase.IEntity;
import josteo.infrastructure.RepositoryFramework.IUnitOfWorkRepository;

/**
 *
 * @author cristiano
 */
public interface IUnitOfWork {
        void RegisterAdded(IEntity entity, IUnitOfWorkRepository repository);
        void RegisterChanged(IEntity entity, IUnitOfWorkRepository repository);
        void RegisterRemoved(IEntity entity, IUnitOfWorkRepository repository);
        void Commit();
        java.util.UUID get_Key();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.RepositoryFramework;

import josteo.infrastructure.DomainBase.IEntity;
/**
 *
 * @author cristiano
 */
public interface IUnitOfWorkRepository {
        void PersistNewItem(IEntity item);
        void PersistUpdatedItem(IEntity item);
        void PersistDeletedItem(IEntity item);
}

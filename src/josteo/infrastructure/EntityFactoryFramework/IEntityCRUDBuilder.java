/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.EntityFactoryFramework;

import josteo.infrastructure.DomainBase.IEntity;
/**
 *
 * @author cristiano
 */
public interface IEntityCRUDBuilder<T extends IEntity, TReturn> {
    TReturn CreateSqlInsert(Object[] FKs, T entity);
    TReturn CreateSqlUpdate(T entity);
    TReturn CreateSqlDelete(T entity);
}

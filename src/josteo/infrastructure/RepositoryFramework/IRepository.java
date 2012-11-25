/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.RepositoryFramework;

import java.util.*;
import josteo.infrastructure.UnitOfWork.IUnitOfWork;
import josteo.infrastructure.DomainBase.IAggregateRoot;

/**
 *
 * @author cristiano
 */
public interface IRepository<T extends IAggregateRoot>{
    void SetUnitOfWork(IUnitOfWork unitOfWork);
    T FindBy(Object key);
    List<T> FindAll();
    void Add(T item);
    void Update(T item);
    void Remove(T item);
}

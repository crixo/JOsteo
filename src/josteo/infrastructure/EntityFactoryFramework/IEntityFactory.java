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
    public interface IEntityFactory<T extends IEntity>{
        T BuildEntity(Object recordset);
    }

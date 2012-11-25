/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.helpers;

import java.util.*;

/**
 *
 * @author cristiano
 */
public class EntityHelper {
    
    public static <TEntity extends josteo.infrastructure.DomainBase.IEntity> TEntity GetItemByKey(List<TEntity> list, Object key){
        TEntity entity = null;
        for( TEntity e : list ){
            if(e.get_Key()==key){
                return e;
            }
        }
        return entity;
    }

}

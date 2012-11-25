/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.DomainBase;

/**
 *
 * @author cristiano
 */
public interface IEntity{
    Object get_Key();
    void set_Key(Object val);
    boolean get_IsChanged();
    void set_IsChanged(boolean val);
    //boolean sameIdentityAs(T other);
}

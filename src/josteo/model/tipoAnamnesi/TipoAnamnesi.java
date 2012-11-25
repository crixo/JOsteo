/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.tipoAnamnesi;

import josteo.infrastructure.DomainBase.*;
/**
 *
 * @author cristiano
 */
public class TipoAnamnesi  extends LookUpItem implements IAggregateRoot{

    public TipoAnamnesi(int key, String descr){
        super(key,"",descr);
    }
    public String toString(){
        return this.getDescription();
    }

    //@Override
    public boolean sameIdentityAs(final TipoAnamnesi other) {
        return other != null && this.get_Key().equals(other.get_Key());
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        final TipoAnamnesi other = (TipoAnamnesi) object;
        return sameIdentityAs(other);
    }
}

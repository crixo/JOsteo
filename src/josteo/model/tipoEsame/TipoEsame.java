/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.tipoEsame;

import josteo.infrastructure.DomainBase.*;
/**
 *
 * @author cristiano
 */
public class TipoEsame extends LookUpItem implements IAggregateRoot{
    public TipoEsame(int key, String descr){
        super(key,"",descr);
    }
    public String toString(){
        return this.getDescription();
    }
    
  //@Override
  public boolean sameIdentityAs(final TipoEsame other) {
    return other != null && this.get_Key().equals(other.get_Key());
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;

    final TipoEsame other = (TipoEsame) object;
    return sameIdentityAs(other);
  }
}


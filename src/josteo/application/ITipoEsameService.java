/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.application;

import josteo.model.tipoEsame.TipoEsame;
import java.util.*;

/**
 *
 * @author cristiano
 */
public interface ITipoEsameService {
    public List<TipoEsame> GetAllEsami();

    public void SaveTipoEsame(TipoEsame entity);

    public TipoEsame GetTipoEsame(Object key);

    public void DeleteEsame(TipoEsame entity);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.application;

import josteo.model.tipoAnamnesi.TipoAnamnesi;
import java.util.*;

/**
 *
 * @author cristiano
 */
public interface ITipoAnamnesiService {
    public List<TipoAnamnesi> GetAllAnamnesi();

    public void SaveTipoAnamnesi(TipoAnamnesi entity);

    public TipoAnamnesi GetTipoAnamnesi(Object key);

    public void DeleteAnamnesi(TipoAnamnesi entity);
}

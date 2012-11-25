/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.application;

import josteo.model.paziente.*;
import java.util.*;

/**
 *
 * @author cristiano
 */
public interface IPazienteService {
    public List<Paziente> GetAllPazienti();

    public void SavePaziente(Paziente paziente);

    public Paziente GetPaziente(Object pazienteKey);

    public void DeletePaziente(Paziente paziente);

    public List<LkpProvincia> GetAllProvince();
}

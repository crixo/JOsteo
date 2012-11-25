/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.paziente;

import josteo.infrastructure.RepositoryFramework.*;
import java.util.*;

/**
 *
 * @author cristiano
 */
public interface IPazienteRepository extends IRepository<Paziente>{
    List<LkpProvincia> GetProvince();
}

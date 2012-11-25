/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.viewmodels;

import josteo.model.paziente.Paziente;
import ca.odell.glazedlists.gui.*;

/**
 *
 * @author cristiano
 */
public class PazienteTableFormat implements TableFormat {
   public int getColumnCount() {
      return 2;
   }
   public String getColumnName(int column) {
      if(column == 0) return "Customer Name";
      else if(column == 1) return "Location";
      else return null;
   }
   public Object getColumnValue(Object baseObject, int column) {
      PazientePresenter paziente = (PazientePresenter)baseObject;
      if(column == 0) return paziente.getFullName();
      else if(column == 1) return paziente.getFullAddress();
      else return null;
   }
}

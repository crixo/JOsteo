/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.views.UI;

import josteo.model.paziente.Paziente;
import ca.odell.glazedlists.gui.*;
import josteo.viewmodels.PazientePresenter;

/**
 *
 * @author cristiano
 */
public class PazienteAdvancedTableFormat implements WritableTableFormat<PazientePresenter> {
   public int getColumnCount() {
      return 2;
   }
   public String getColumnName(int column) {
      if(column == 0) return "Paziente";
      else if(column == 1) return "Inidirizzo";
      else return null;
   }
   public Object getColumnValue(PazientePresenter pazienteP, int column) {
      //PazientePresenter paziente = (PazientePresenter)baseObject;
      if(column == 0) return pazienteP.getFullName();
      else if(column == 1) return pazienteP.getFullAddress();
      else return null;
   }
   public boolean isEditable(PazientePresenter pazienteP, int column){
      if(column == 0) return false;
      else if(column == 1) return false;
      else return false;
   }
   public PazientePresenter setColumnValue(PazientePresenter pazienteP, Object o, int column){
      if(column == 0) return pazienteP;
      else if(column == 1) return pazienteP;
      else return null;
   }
}

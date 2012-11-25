/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.views.UI;

import josteo.model.paziente.*;
import ca.odell.glazedlists.gui.*;

/**
 *
 * @author cristiano
 */
public class TrattamentoAdvancedTableFormat implements WritableTableFormat<Trattamento> {
   public int getColumnCount() {
      return 2;
   }
   public String getColumnName(int column) {
      if(column == 0) return "Data";
      else if(column == 1) return "Descrizione";
      else return null;
   }
   public Object getColumnValue(Trattamento item, int column) {
      //PazientePresenter paziente = (PazientePresenter)baseObject;
      if(column == 0) return item.get_Note().get_Data();
      else if(column == 1) return item.get_Note().get_Descrizione();
      else return null;
   }
   public boolean isEditable(Trattamento item, int column){
      return false;
   }
   public Trattamento setColumnValue(Trattamento item, Object o, int column){
      return null;
   }
}

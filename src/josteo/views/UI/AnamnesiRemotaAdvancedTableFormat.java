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
public class AnamnesiRemotaAdvancedTableFormat implements WritableTableFormat<AnamnesiRemota> {
   public int getColumnCount() {
      return 3;
   }
   public String getColumnName(int column) {
      if(column == 0) return "Data";
      else if(column == 1) return "Tipo";
      else if(column == 2) return "Descrizione";
      else return null;
   }
   public Object getColumnValue(AnamnesiRemota item, int column) {
      //PazientePresenter paziente = (PazientePresenter)baseObject;
      if(column == 0) return java.text.DateFormat.getDateInstance().format(item.get_Note().get_Data());
      else if(column == 1) return item.get_Tipo().getDescription();
      else if(column == 2) return item.get_Note().get_Descrizione();
      else return null;
   }
   public boolean isEditable(AnamnesiRemota item, int column){
      return false;
   }
   public AnamnesiRemota setColumnValue(AnamnesiRemota item, Object o, int column){
      if(column == 0) return item;
      else if(column == 1) return item;
      else if(column == 2) return item;
      else return null;
   }
}

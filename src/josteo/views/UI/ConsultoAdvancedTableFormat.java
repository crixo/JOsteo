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
public class ConsultoAdvancedTableFormat implements WritableTableFormat<Consulto> {
   public int getColumnCount() {
      return 2;
   }
   public String getColumnName(int column) {
      if(column == 0) return "Data";
      else if(column == 1) return "ProblemaIniziale";
      else return null;
   }
   public Object getColumnValue(Consulto item, int column) {
      //PazientePresenter paziente = (PazientePresenter)baseObject;
      if(column == 0) return java.text.DateFormat.getDateInstance().format(item.get_Data());
      else if(column == 1) return item.get_ProblemaIniziale();
      else return null;
   }
   public boolean isEditable(Consulto item, int column){
      return false;
   }
   public Consulto setColumnValue(Consulto item, Object o, int column){
      if(column == 0) return item;
      else if(column == 1) return item;
      else return null;
   }
}

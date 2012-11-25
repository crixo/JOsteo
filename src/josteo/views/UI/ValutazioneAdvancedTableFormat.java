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
public class ValutazioneAdvancedTableFormat implements WritableTableFormat<Valutazione> {
   public int getColumnCount() {
      return 3;
   }
   public String getColumnName(int column) {
      if(column == 0) return "Strutturale";
      else if(column == 1) return "Cranio-Sacrale";
      else if(column == 2) return "Ak-Ortodontica";
      else return null;
   }
   public Object getColumnValue(Valutazione item, int column) {
      //PazientePresenter paziente = (PazientePresenter)baseObject;
      if(column == 0) return item.get_Strutturale();
      else if(column == 1) return item.get_CranioSacrale();
      else if(column == 2) return item.get_AkOrtodontica();
      else return null;
   }
   public boolean isEditable(Valutazione item, int column){
      return false;
   }
   public Valutazione setColumnValue(Valutazione item, Object o, int column){
      return null;
   }
}

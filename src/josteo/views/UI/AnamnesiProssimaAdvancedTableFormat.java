/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.views.UI;

import josteo.model.paziente.*;
import ca.odell.glazedlists.gui.*;
import javax.swing.JCheckBox;

/**
 *
 * @author cristiano
 */
public class AnamnesiProssimaAdvancedTableFormat implements WritableTableFormat<AnamnesiProssima> {
   public int getColumnCount() {
      return 3;
   }
   public String getColumnName(int column) {
      if(column == 0) return "PrimaVolta";
      else if(column == 1) return "Tipologia";
      else if(column == 2) return "Localizzazione";
//      else if(column == 3) return "irradiazione";
//      else if(column == 4) return "Periodo Insorgenza";
//      else if(column == 5) return "Durata";
//      else if(column == 6) return "Familiarità";
//      else if(column == 7) return "Altre terapie";
//      else if(column == 8) return "Varie";
      else return null;
   }
   public Object getColumnValue(AnamnesiProssima item, int column) {
      //PazientePresenter paziente = (PazientePresenter)baseObject;
      if(column == 0) return (item.get_PrimaVolta().length()==0? "" : (item.get_PrimaVolta().compareTo("1")==0? "Sì" : "No"));
      else if(column == 1) return item.get_Tipologia();
      else if(column == 2) return item.get_Localizzazione();
//      else if(column == 3) return item.get_Irradiazione();
//      else if(column == 4) return item.get_PeriodoInsorgenza();
//      else if(column == 5) return item.get_Durata();
//      else if(column == 6) return item.get_Familiarita();
//      else if(column == 7) return item.get_AltreTerapie();
//      else if(column == 8) return item.get_Varie();
      else return null;
   }
   public boolean isEditable(AnamnesiProssima item, int column){
      return false;
   }
   public AnamnesiProssima setColumnValue(AnamnesiProssima item, Object o, int column){
      return null;
   }
}

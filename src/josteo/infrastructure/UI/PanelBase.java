/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.UI;



/**
 *
 * @author cristiano
 */
public abstract class PanelBase extends javax.swing.JPanel {
    protected PanelBase(){
        
    }

    protected boolean _isDirty;
    public abstract void Init();
}

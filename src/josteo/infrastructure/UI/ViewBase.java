/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.UI;


import java.beans.PropertyChangeEvent;

import javax.swing.event.*;
import javax.swing.UIManager;

/**
 *
 * @author cristiano
 */
public abstract class ViewBase extends javax.swing.JFrame {
    protected ViewBase(){
    }

    protected boolean _isDirty;
}

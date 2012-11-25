/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ImportView.java
 *
 * Created on Nov 25, 2010, 8:49:31 AM
 */

package josteo.views;
import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.io.*;

import josteo.infrastructure.UI.*;
import josteo.infrastructure.helpers.*;
import org.xml.sax.SAXException;

/**
 *
 * @author cristiano
 */
public class LoggingView extends ViewBase implements josteo.infrastructure.UI.IView {

    String _choosertitle;
    String _loggingFolder;

    /** Creates new form ImportView */
    public LoggingView() {
        initComponents();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSelectFolder = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblLoggingFolder = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();

        btnSelectFolder.setText("Change Logging Folder");
        btnSelectFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFolderActionPerformed(evt);
            }
        });

        lblLoggingFolder.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lblLoggingFolder.setText("jLabel1");

        lbl.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        lbl.setText("Logging Folder");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblLoggingFolder, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 441, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lbl))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(lbl)
                .add(4, 4, 4)
                .add(lblLoggingFolder, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(btnSelectFolder)))
                .addContainerGap(341, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(31, 31, 31)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnSelectFolder)
                .addContainerGap(358, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFolderActionPerformed
        // TODO add your handling code here:
        int result;

        JFileChooser chooser = josteo.views.UI.Helpers.CreateFileChooser(this, _choosertitle, JFileChooser.DIRECTORIES_ONLY);

        _loggingFolder = chooser.getSelectedFile().getAbsolutePath();
        ConfigHelper.getInstance().getProperties().setProperty("logging.folderPath",_loggingFolder);
        ConfigHelper.getInstance().getProperties().StoreProperties();
        this.lblLoggingFolder.setText(_loggingFolder);
    }//GEN-LAST:event_btnSelectFolderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelectFolder;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblLoggingFolder;
    // End of variables declaration//GEN-END:variables

    public void Init(){
        String loggingFolder = ConfigHelper.getInstance().getProperties().getProperty("logging.folderPath");
        _loggingFolder = loggingFolder==null? "" : loggingFolder;
        if(!_loggingFolder.isEmpty()){
            File exportDir = new File(_loggingFolder);
            if(!exportDir.exists())
                this._loggingFolder = "";
        }
        System.out.println("loggingFolder:"+loggingFolder);
        this.lblLoggingFolder.setText(_loggingFolder.isEmpty()? "--not set--" : _loggingFolder);
    }

}
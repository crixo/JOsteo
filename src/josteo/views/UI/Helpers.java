/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.views.UI;
import javax.swing.*;

/**
 *
 * @author cristiano
 */
public class Helpers {
    public static JFileChooser CreateFileChooser(java.awt.Component hoster, String choosertitle, int selectionMode){
        int result;

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(selectionMode);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(hoster) == JFileChooser.APPROVE_OPTION) {

            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());
        }else {
          System.out.println("No Selection ");
        }

        return chooser;
    }
}

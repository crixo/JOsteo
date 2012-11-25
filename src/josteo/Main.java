/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo;

import java.util.*;
import java.io.*;
import com.apple.eawt.Application;
import com.apple.eawt.AboutHandler;
import com.apple.eawt.AppEvent.AboutEvent;
import josteo.infrastructure.helpers.*;

/**
 *
 * @author cristiano
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) throws Exception{
        if(System.getProperty("os.name").startsWith("Mac")){
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", ConfigHelper.getInstance().getAppName());

            java.awt.image.BufferedImage image = null;
            try {
                image = javax.imageio.ImageIO.read(Main.class.getClass().getResource("/josteo-icon.png"));
            } catch (java.io.IOException e) {
                System.out.println(e.getMessage());
            }
            //this.setIconImage(image);
            com.apple.eawt.Application.getApplication().setDockIconImage(image);

            // create an instance of the Mac Application class, so i can handle the
            // mac quit event with the Mac ApplicationAdapter
            Application macApplication = Application.getApplication();
            //MyApplicationAdapter macAdapter = new MyApplicationAdapter(this);
            //macApplication.addApplicationListener(macAdapter);
            macApplication.setAboutHandler(new AboutHandler() {
                public void handleAbout(AboutEvent ae) {
                    javax.swing.JOptionPane.showMessageDialog(null, ConfigHelper.getInstance().getFullAppName());
                }
            });

            // need to enable the preferences option manually
            //macApplication.setEnabledPreferencesMenu(true);

        }

        josteo.infrastructure.helpers.ConfigHelper.getInstance().WriteLog("Application started");

        if(!ConfigHelper.getInstance().getMissingProperties().isEmpty()){
            String msg = "You need to set following properties:\n" +  StringHelper.join(ConfigHelper.getInstance().getMissingProperties(), "\n");
            javax.swing.JOptionPane.showMessageDialog(null, msg);
        }

        

//listPackages();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                josteo.infrastructure.UI.IView view = (josteo.infrastructure.UI.IView)josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("HomeView");//new josteo.views.HomeView();
                josteo.application.ApplicationState.getInstance().setCurrentView(view);
                view.setVisible(true);
                view.Init();
            }
        });



        
    }

  public static void listPackages() {
    Package[] allPackages = Package.getPackages();
    System.out.println("All loaded packages:");
    for(int i=0; i < allPackages.length; i++) {
          System.out.println("" + (i+1) +
            ": " + allPackages[i].getName() +
            ": " + allPackages[i].getImplementationTitle() +
            ", version: " + allPackages[i].getImplementationVersion());
    }
  }
}

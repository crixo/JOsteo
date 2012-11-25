/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo;

import java.io.*;
import java.util.*;

/**
 *
 * @author cristiano
 */
public class ConfigLoader {

    public ConfigLoader(){
            //configFile.load(this.getClass().getClassLoader().getResourceAsStream("/app.properties"));
            System.out.println(getLocalDirName());

            try
            {

                ResourceBundle bdl = new PropertyResourceBundle(new FileInputStream(getLocalDirName() + "/josteo.properties"));
                String connStr = bdl.getString("connStr");
                System.out.println(connStr);
                Properties p = new Properties();
                Enumeration keys = bdl.getKeys();

                while(  keys.hasMoreElements( ) ) {
                    String prop = (String)keys.nextElement( );
                    String val = bdl.getString(prop);

                    p.setProperty(prop, val);
                }

                //Class.forName(bdl.getString("driver")).newInstance( );
            }
            catch(Exception exc)
            {
                System.out.println(exc.getMessage());
            }
    }

public String getLocalDirName()
   {
      String localDirName;

      //Use that name to get a URL to the directory we are executing in
      java.net.URL myURL = this.getClass().getResource(getClassName());  //Open a URL to the our .class file

      //Clean up the URL and make a String with absolute path name
      localDirName = myURL.getPath();  //Strip path to URL object out
      localDirName = myURL.getPath().replaceAll("%20", "");  //change %20 chars to spaces

      //Get the current execution directory
      localDirName = localDirName.substring(0,localDirName.lastIndexOf("/"));  //clean off the filename

      return localDirName;
   }

public String getClassName()
   {
      String thisClassName;

      //Build a string with executing class's name
      thisClassName = this.getClass().getName();
      thisClassName = thisClassName.substring(thisClassName.lastIndexOf(".") + 1,thisClassName.length());
      thisClassName += ".class";  //this is the name of thebytecode file that is executing

      return thisClassName;
   }

}

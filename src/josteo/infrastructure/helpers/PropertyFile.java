/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.helpers;

/**
 *
 * @author cristiano
 */

// -----------------------------------------------------------------------------
// PropertyFiles.java
// -----------------------------------------------------------------------------

import java.util.Properties;
import java.util.Enumeration;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 * -----------------------------------------------------------------------------
 * Used to provide an example of how to get/modify/save Properties files
 * while preserving comments in the file.
 * -----------------------------------------------------------------------------
 */

public class PropertyFile {
    private String _filePath;
    private Properties _properties;

    public PropertyFile(String filePath){
        this._filePath = filePath;
        _properties = new Properties();
        loadProperties();

    }

	private static void printProperties(Properties p, String s) {

		System.out.println();
		System.out.println();

		System.out.println(s);

		System.out.println();

		p.list(System.out);
		System.out.println();
	}


	private void loadProperties() {

            InputStream inPropFile;

            try {
                    inPropFile = new FileInputStream(this._filePath);
                    this._properties.load(inPropFile);
                    inPropFile.close();
            } catch (IOException ioe) {
                    System.out.println("I/O Exception.");
                    ioe.printStackTrace();
                    System.exit(0);
            }

	}


	public void setProperty(String key, String value) {
            this._properties.setProperty(key, value);
	}

	public String getProperty(String key) {
            return this._properties.getProperty(key);
	}


	public void StoreProperties() {

            OutputStream outPropFile;

            try {
                    outPropFile = new FileOutputStream(this._filePath);
                    this._properties.store(outPropFile, "Properties File");
                    outPropFile.close();
            } catch (IOException ioe) {
                    System.out.println("I/O Exception.");
                    ioe.printStackTrace();
                    System.exit(0);
            }

	}



}



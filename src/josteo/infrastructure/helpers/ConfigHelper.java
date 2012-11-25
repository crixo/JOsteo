/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.helpers;
import java.util.*;
import java.io.*;

/**
 *
 * @author cristiano
 */
public class ConfigHelper {

    private static ConfigHelper singleton;

    public static ConfigHelper getInstance() {
      if(singleton == null) {
         synchronized(ConfigHelper.class) {
           if(singleton == null) {
             singleton = new ConfigHelper();
           }
        }
      }
      return singleton;
    }

    private String _appVersion;
    private String _appName;
    private String _connStr;
    private String _logFile;
    private String _macUser;
    private String _sRootPath;
    private PropertyFile _properties;
    private List<String> _missingProperties;

    public String getFullAppName(){ return this._appName + " " + this._appVersion; }
    public String getAppName(){ return this._appName; }
    public String get_ConnStr(){ return this._connStr; }
    public String getRootPath(){ return this._sRootPath; }

    public PropertyFile getProperties(){ return _properties; }
    public List<String> getMissingProperties(){ return _missingProperties; }

    private ConfigHelper(){
        try
        {
            this._appVersion = "1.0.0.0";
            this._appName = "JOsteo";
            this._missingProperties = new ArrayList<String>();

            this._macUser = System.getProperty("user.name");
            
            System.out.println("ConfigHelper1:"+getLocalDirName());
            String sRootPath = getLocalDirName().substring(0,getLocalDirName().indexOf("/josteo"));
            System.out.println("ConfigHelper2:"+sRootPath);
            _sRootPath = sRootPath+'/';
            String sRootLevelPath = sRootPath.substring(0,sRootPath.lastIndexOf("/"));
            System.out.println("ConfigHelper3:"+sRootLevelPath);
            String sPropertiesFilePath = sRootLevelPath+"/josteo.properties";
            System.out.println("ConfigHelper4:"+sPropertiesFilePath);

            sPropertiesFilePath = sPropertiesFilePath.replaceFirst("file:", "");

            File f = new File(sPropertiesFilePath);
            System.out.println(f + (f.exists()? " is found " : " is missing "));
            sPropertiesFilePath = f.exists()? sPropertiesFilePath : "/etc/josteo.properties";

            f = new File("/etc/josteo.properties");
            System.out.println(f + (f.exists()? " is found " : " is missing "));

            _properties = new PropertyFile(sPropertiesFilePath);
            String host = _properties.getProperty("db.host");
            String port = _properties.getProperty("db.port");
            String dbname = _properties.getProperty("db.dbname");
            String user = _properties.getProperty("db.user");
            String pwd = _properties.getProperty("db.password");
            String binFolder = _properties.getProperty("db.binFolder");
            this._connStr = String.format("jdbc:mysql://%1$s/%2$s?user=%3$s&password=%4$s", host, dbname, user, pwd);

            this._macUser = System.getProperty("user.name");

            this._logFile = "";
            String loggingFolderPath = _properties.getProperty("logging.folderPath");//.replaceFirst("\\$username\\$", this._macUser);
            if(loggingFolderPath!=null){
                String loggingFileName = String.format(_properties.getProperty("logging.fileName"), Calendar.getInstance());
                this._logFile = org.apache.commons.io.FilenameUtils.concat(loggingFolderPath,loggingFileName);
            }else{
                this._missingProperties.add("logging.folderPath");
            }



        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    public void WriteLog(String logMsg){

        try
        {
            System.out.println(logMsg);

            if(!this._logFile.isEmpty()){
                PrintWriter pw = new PrintWriter(new FileWriter(_logFile,true));
                pw.println("["+ DateHelper.CreateSortedFormatter().format(new java.util.Date()) +"]\t"+logMsg);
                pw.close();
            }
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    public void WriteLog(Exception exc){

        // Create a StringWriter and a PrintWriter both of these object
        // will be used to convert the data in the stack trace to a string.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        // Instead of writting the stack trace in the console we write it
        // to the PrintWriter, to get the stack trace message we then call
        // the toString() method of StringWriter.
        //
        exc.printStackTrace(pw);
        System.out.println("Error = " + sw.toString());

        WriteLog(sw.toString());
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



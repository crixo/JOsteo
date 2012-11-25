/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.helpers;

import java.io.FileInputStream;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.FileSystemResource;

/**
 *
 * @author cristiano
 */
public class ContainerHelper {
    private static BeanFactory container;

    private static final int N = 100;

    static {
            container = getBeanFactory();
    }

    public static BeanFactory getContainer(){ return container; }

    private static BeanFactory getBeanFactory(){
        BeanFactory factory = null;
        try{
//            InputStream in=getClass().getResourceAsStream("resources/textfile");
//            BufferedReader br=new BufferedReader(new InputStreamReader(in));

            // get the bean factory
            //factory = new XmlBeanFactory(new FileSystemResource(containerPath));//"/Users/cristiano/NetBeansProjects/JOsteo/src/beans.xml"
            factory = new XmlBeanFactory( new InputStreamResource(ContainerHelper.class.getClass().getResourceAsStream("/beans.xml")));
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
        }
        return factory;
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.helpers;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author cristiano
 */
public class DateHelper {

    public static SimpleDateFormat CreateSortedFormatter(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static SimpleDateFormat CreateFormatter(String format){
        return new SimpleDateFormat(format);
    }

}

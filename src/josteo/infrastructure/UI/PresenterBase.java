/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.UI;


import ca.odell.glazedlists.*;

/**
 *
 * @author cristiano
 */
public abstract class PresenterBase {
    protected EventList<String> Messages;

    public EventList<String> getMessages(){ return this.Messages;}

    public PresenterBase(){
        this.Messages = new BasicEventList<String>();
    }

    public String MessagesToString(String separator){
        return josteo.infrastructure.helpers.StringHelper.join(this.Messages, separator);
    }

}

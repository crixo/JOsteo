/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.DomainBase;

import java.util.*;

/**
 *
 * @author cristiano
 */
    public abstract class BrokenRuleMessages
    {
        private Map<String, String> _messages;

        protected Map<String, String> get_Messages()
        {
            return this._messages;
        }

        protected BrokenRuleMessages()
        {
            this._messages = new TreeMap<String, String>();
            //this.PopulateMessages();
        }

        protected abstract void PopulateMessages();

        public String GetRuleDescription(String messageKey)
        {
            String description = "";
            if (this._messages.containsKey(messageKey))
            {
                description = this._messages.get(messageKey);
            }
            return description;
        }
    }

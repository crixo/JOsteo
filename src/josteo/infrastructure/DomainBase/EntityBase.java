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
public abstract class EntityBase implements IEntity  {
    private Object _key;
    private List<BrokenRule> _brokenRules;
    private BrokenRuleMessages _brokenRuleMessages;
    private boolean _isChanged;

    protected EntityBase(){
        this(null);
    }

    protected EntityBase(Object key){
        this._key = key;

        this._brokenRules = new ArrayList<BrokenRule>();
        //this._brokenRuleMessages = this.GetBrokenRuleMessages();
    }

    public Object get_Key(){
        return _key;
    }
    
    public void set_Key(Object val){
        this._key=val;
    }    

    public boolean get_IsChanged(){
        return this._isChanged;
    }
    public void set_IsChanged(boolean val){
        this._isChanged = val;
    }

    protected abstract void Validate();

    protected abstract BrokenRuleMessages GetBrokenRuleMessages();

    protected List<BrokenRule> get_BrokenRules(){
        return this._brokenRules;
    }

    public List<BrokenRule> GetBrokenRules(){
        this.Validate();
        return this._brokenRules;
    }

    protected void AddBrokenRule(String messageKey){
        this._brokenRules.add(new BrokenRule(messageKey,
            this._brokenRuleMessages.GetRuleDescription(messageKey)));
    }
}

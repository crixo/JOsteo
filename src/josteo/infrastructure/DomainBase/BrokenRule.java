/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.DomainBase;

/**
 *
 * @author cristiano
 */
public class BrokenRule {
    private String _name;
    private String _description;

    public BrokenRule(String name, String description)
    {
        this._name = name;
        this._description = description;
    }

    public String get_Name(){
        return this._name;
    }

    public String get_Description(){
        return this._description;
    }

    public String get_Message(){
        return this._name + ":" + this._description;
    }
}

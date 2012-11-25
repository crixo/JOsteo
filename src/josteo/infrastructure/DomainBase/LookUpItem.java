/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.DomainBase;


/**
 *
 * @author cristiano
 */
public abstract class LookUpItem extends EntityBase {
    private String _code;
    private String _descr;

    public String getCode(){return this._code;}
    public String getDescription(){return this._descr;}

    public LookUpItem(){
        this(0,"","");
    }

    public LookUpItem(int key, String code, String descr){
        super(key);
        this._code = code;
        this._descr = descr;
    }

    @Override
    protected BrokenRuleMessages GetBrokenRuleMessages(){
        return null;
    }

    @Override
    protected void Validate(){
        if(this._descr.length()==0){
            this.get_BrokenRules().add(new BrokenRule("Descrizione","cannot be empty"));
        }

    }

}

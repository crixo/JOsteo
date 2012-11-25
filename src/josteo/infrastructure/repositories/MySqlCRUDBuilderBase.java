/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories;

import java.sql.*;
import josteo.infrastructure.DomainBase.IEntity;
import josteo.infrastructure.helpers.ConfigHelper;

/**
 *
 * @author cristiano
 */
public abstract class MySqlCRUDBuilderBase<TEntity extends IEntity> {

    protected Connection Database;
    protected String TableName;

    protected MySqlCRUDBuilderBase(Connection conn){
        this.Database = conn;
    }

    protected java.sql.Date convertToSqlDate(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }

    public PreparedStatement CreateSqlLogicalCancel(TEntity entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE "+this.TableName+" ");
        sb.append("SET ");
        sb.append("LCV=ID ");
        sb.append("WHERE ID=?");

        try{
            stm = this.Database.prepareStatement(sb.toString());
            stm.setInt(1, java.lang.Math.abs((Integer)entity.get_Key()));
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }

    public PreparedStatement CreateSqlDelete(TEntity entity){
        PreparedStatement stm = null;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM "+this.TableName+" ");
        sb.append("WHERE ID=?");

        try{
            stm = this.Database.prepareStatement(sb.toString());
            stm.setInt(1, java.lang.Math.abs((Integer)entity.get_Key()));
        }catch(Exception exc){
            ConfigHelper.getInstance().WriteLog(exc);
            stm = null;
        }

        return stm;
    }
}

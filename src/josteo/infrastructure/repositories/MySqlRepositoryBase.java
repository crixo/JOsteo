/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.repositories;

import josteo.infrastructure.helpers.ConfigHelper;
import java.sql.*;
//import com.mysql.jdbc.PreparedStatement;

import josteo.infrastructure.DomainBase.*;
import josteo.infrastructure.UnitOfWork.*;
import josteo.infrastructure.RepositoryFramework.*;
import josteo.infrastructure.EntityFactoryFramework.*;
import josteo.infrastructure.helpers.ConfigHelper;


/**
 *
 * @author cristiano
 */

public abstract class MySqlRepositoryBase<T extends IAggregateRoot> extends RepositoryBase<T>{



    protected java.sql.Connection database;
    protected IEntityFactory<T> EntityFactory;
    //private Dictionary<string, AppendChildData> childCallbacks;
    private String _baseQuery;
    private String _baseWhereClause;
    private String _entityName;
    private String _keyFieldName;


    protected MySqlRepositoryBase(){
        this(null);
    }

    protected MySqlRepositoryBase(IUnitOfWork unitOfWork){
        super(unitOfWork);

        try
        {
            this.database = DriverManager.getConnection(ConfigHelper.getInstance().get_ConnStr());
        }
        catch(Exception exc)
        {
            ConfigHelper.getInstance().WriteLog(exc.getMessage());
        }
    }

    protected java.sql.Connection get_Database(){
        return this.database;
    }

    protected ResultSet ExecuteQuery(String sql) throws SQLException{
        Statement stmt = this.database.createStatement();
        ConfigHelper.getInstance().WriteLog(sql);
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    protected ResultSet ExecuteQuery(PreparedStatement stmt) throws SQLException{
        //Statement stmt = this.database.createStatement();
        ConfigHelper.getInstance().WriteLog(stmt.toString());
        ResultSet rs = stmt.executeQuery();
        return rs;
    }
    
    protected int ExecuteNoQuery(PreparedStatement stmt) throws SQLException{
        //Statement stmt = this.database.createStatement();
        ConfigHelper.getInstance().WriteLog(stmt.toString());
        return stmt.executeUpdate();
    }

    protected int ExecuteInsert(PreparedStatement stmt) throws SQLException{
        //Statement stmt = this.database.createStatement();
        ConfigHelper.getInstance().WriteLog(stmt.toString());
        stmt.executeUpdate();
        int autoIncKeyFromApi=-1;
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            autoIncKeyFromApi = rs.getInt(1);
        } else {

            // throw an exception from here
        }

        rs.close();
        return autoIncKeyFromApi;

//String sql = "INSERT INTO table (column1, column2) values(?, ?)";
//stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//
//stmt.executeUpdate();
//if(returnLastInsertId) {
//ResultSet rs = stmt.getGeneratedKeys();
//rs.next();
//auto_id = rs.getInt(1);
//}
    }
    
    protected <TEntity extends IEntity> void DoCrud(TEntity entity, IEntityCRUDBuilder<TEntity, PreparedStatement> crudBuilder, Object[] fks) throws SQLException{
       int key = (Integer)entity.get_Key();
        if(key<0){
            this.ExecuteNoQuery(crudBuilder.CreateSqlDelete(entity));
       } else if (key==0) {
            int newID = this.ExecuteInsert(crudBuilder.CreateSqlInsert(fks, entity));
            entity.set_Key(newID);
        }else if(entity.get_IsChanged()){
            this.ExecuteNoQuery(crudBuilder.CreateSqlUpdate(entity));   
            entity.set_IsChanged(false);
       }

        //PreparedStatement pstmt = this.database.prepareStatement( "" );
        //pstmt.setString( 1, "");
    }

    protected abstract StringBuilder GetBaseQueryBuilder();
}

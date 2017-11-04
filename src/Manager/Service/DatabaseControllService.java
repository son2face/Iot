package Manager.Service;


import Manager.Entity.DatabaseEntity;
import Manager.Interface.IDatabaseControllService;
import Manager.Model.DatabaseModel;
import Module.Node.NodeModel;
import Module.NodeValue.NodeValueModel;
import org.hibernate.cfg.Configuration;

/**
 * Created by Son on 5/12/2017.
 */

public class DatabaseControllService implements IDatabaseControllService {
    public boolean setActive(int id) {
        if (id >= 0 && id < DatabaseEntity.getDatabaseModels().size()) {
            DatabaseEntity.setActive(id);
            return true;
        }
        return false;
    }

    public Configuration createConfiguration(DatabaseModel databaseModel) {
        Configuration cfg;
        switch (databaseModel.typeDB) {
            case 0:
                cfg = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                        .setProperty("hibernate.connection.url", "jdbc:mysql://" + databaseModel.url + "/" + databaseModel.databaseName)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect")
                        .setProperty("hibernate.connection.username", databaseModel.userName)
                        .setProperty("hibernate.connection.password", databaseModel.passWord)
                        .setProperty("hibernate.hbm2ddl.auto", "update")
                        .setProperty("hibernate.connection.autoReconnect", "true")
                        .setProperty("hibernate.connection.verifyServerCertificate","false")
                        .addAnnotatedClass(NodeModel.class)
                        .addAnnotatedClass(NodeValueModel.class);
//                        .addAnnotatedClass(FileModel.class)
//                        .addAnnotatedClass(UserModel.class);
                break;
            default:
                cfg = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
                        .setProperty("hibernate.connection.url", "jdbc:sqlserver:://" + databaseModel.url + "/" + databaseModel.databaseName)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect")
                        .setProperty("hibernate.connection.username", databaseModel.userName)
                        .setProperty("hibernate.connection.password", databaseModel.passWord)
                        .setProperty("hibernate.hbm2ddl.auto", "update")
                        .setProperty("hibernate.id.new_generator_mappings","false")
                        .setProperty("hibernate.connection.autoReconnect", "true")
                        .setProperty("hibernate.connection.verifyServerCertificate","false")
                        .addAnnotatedClass(NodeValueModel.class)
                        .addAnnotatedClass(NodeModel.class);
//                        .addAnnotatedClass(FileModel.class)
//                        .addAnnotatedClass(UserModel.class);
                break;
        }
        return cfg;
    }
}

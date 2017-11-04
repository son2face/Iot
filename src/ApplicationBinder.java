import Manager.Entity.DatabaseEntity;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import Module.Node.NodeService;
import Module.NodeValue.NodeValueService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.SessionFactory;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        DatabaseService databaseService = new DatabaseService();
        DatabaseControllService databaseControllService = new DatabaseControllService();
        SessionFactory factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
        bind(databaseService).to(DatabaseService.class);
        bind(databaseControllService).to(DatabaseControllService.class);
        bind(new NodeValueService(factory)).to(NodeValueService.class);
        bind(new NodeService(factory)).to(NodeService.class);
//        bind(new FileService()).to(FileService.class);
//        bind(new UserService()).to(UserService.class);
    }
}
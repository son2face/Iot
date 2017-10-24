import Manager.Entity.DatabaseEntity;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import Module.Edge.EdgeService;
import Module.File.FileService;
import Module.Point.PointService;
import Module.Problem.ProblemService;
import Module.Shape.ShapeService;
import Module.User.UserService;
import org.glassfish.jersey.server.ResourceConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class InitClass extends ResourceConfig implements ServletContextListener {

    public void contextInitialized(ServletContextEvent arg0) {
        DatabaseEntity.setFileDir("database.txt");
        DatabaseEntity.loadData();
        register(new EdgeService());
        register(new FileService());
        register(new PointService());
        register(new ProblemService());
        register(new ShapeService());
        register(new UserService());
        register(new DatabaseControllService());
        register(new DatabaseService());
//        File file = new File(Tools.FullPath + GetPDF.LOCAL_URL);
//        System.out.println(file.getPath());
//        System.out.println(file.getAbsolutePath());
//        DiemThi.makeData(file);
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            DatabaseEntity.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
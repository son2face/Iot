package Manager.Interface;

import Manager.Model.DatabaseModel;
import org.hibernate.cfg.Configuration;

/**
 * Created by Son on 5/12/2017.
 */
public interface IDatabaseControllService {
    Configuration createConfiguration(DatabaseModel databaseModel);

    boolean setActive(int id);
}

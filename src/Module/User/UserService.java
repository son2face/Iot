package Module.User;

import Manager.Entity.DatabaseEntity;
import Manager.Interface.IDatabaseControllService;
import Manager.Interface.IDatabaseService;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import com.google.common.collect.Lists;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Son on 6/15/2017.
 */
public class UserService {
    private static SessionFactory factory;
    private static int currentActive;

    public UserService(SessionFactory factory) {
        this.factory = factory;
    }

    public UserService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        UserService.factory = factory;
    }


    public UserModel get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserEntity> userEntities = criteria.from(UserEntity.class);
        criteria.where(builder.equal(userEntities.get("userId"), id));
        try {
            UserEntity userEntity = session.createQuery(criteria).getSingleResult();
            return new UserModel(userEntity);
        } catch (NoResultException e) {
            return null;
        }
    }

    public UserModel create(int userId, String userName, String passWord) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserModel userModel = new UserModel(userId, userName, passWord);
            int id = Integer.valueOf(String.valueOf(session.save(userModel.toEntity())));
            tx.commit();
            UserModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public UserModel create(UserModel userModel) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int id = Integer.valueOf(String.valueOf(session.save(userModel.toEntity())));
            tx.commit();
            UserModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public UserModel update(int userId, String userName, String passWord) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserModel userModel = new UserModel(userId, userName, passWord);
            session.update(userModel.toEntity());
            tx.commit();
            UserModel result = get(userId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public UserModel update(int userId, UserModel userModel) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(userModel.toEntity());
            tx.commit();
            UserModel result = get(userId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public boolean delete(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(id);
            session.delete(userEntity);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public List<UserModel> get(SearchUserModel searchUserModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserEntity> UserEntities = criteria.from(UserEntity.class);
        try {
            List<UserEntity> userEntities = session.createQuery(criteria).getResultList();
            return Lists.transform(userEntities, userEntity -> new UserModel(userEntity));
        } catch (NoResultException e) {
            return null;
        }
    }
}

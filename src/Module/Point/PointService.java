package Module.Point;

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
public class PointService {
    private static SessionFactory factory;
    private static int currentActive;

    public PointService(SessionFactory factory) {
        this.factory = factory;
    }

    public PointService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        PointService.factory = factory;
    }

    public PointModel get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PointEntity> criteria = builder.createQuery(PointEntity.class);
        Root<PointEntity> pointEntities = criteria.from(PointEntity.class);
        criteria.where(builder.equal(pointEntities.get("pointId"), id));
        try {
            PointEntity pointEntity = session.createQuery(criteria).getSingleResult();
            return new PointModel(pointEntity);
        } catch (NoResultException e) {
            return null;
        }
    }

    public PointModel create(PointModel pointModel) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int id = Integer.valueOf(String.valueOf(session.save(pointModel.toEntity())));
            tx.commit();
            PointModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public PointModel create(int pointId, Integer x, Integer y) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            PointModel pointModel = new PointModel(pointId, x, y);
            int id = Integer.valueOf(String.valueOf(session.save(pointModel.toEntity())));
            tx.commit();
            PointModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public PointModel update(int pointId, Integer x, Integer y) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            PointModel pointModel = new PointModel(pointId, x, y);
            session.update(pointModel.toEntity());
            tx.commit();
            PointModel result = get(pointId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public PointModel update(int pointId, PointModel pointModel) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(pointModel.toEntity());
            tx.commit();
            PointModel result = get(pointId);
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
            PointEntity pointEntity = new PointEntity();
            pointEntity.setPointId(id);
            session.delete(pointEntity);
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

    public List<PointModel> get(SearchPointModel searchPointModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PointEntity> criteria = builder.createQuery(PointEntity.class);
        Root<PointEntity> PointEntities = criteria.from(PointEntity.class);
        try {
            List<PointEntity> pointEntities = session.createQuery(criteria).getResultList();
            return Lists.transform(pointEntities, pointEntity -> new PointModel(pointEntity));
        } catch (NoResultException e) {
            return null;
        }
    }
}

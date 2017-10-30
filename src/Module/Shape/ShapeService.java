package Module.Shape;

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
public class ShapeService {
    private static SessionFactory factory;
    private static int currentActive;

    public ShapeService(SessionFactory factory) {
        this.factory = factory;
    }

    public ShapeService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        ShapeService.factory = factory;
    }


    public ShapeModel get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ShapeEntity> criteria = builder.createQuery(ShapeEntity.class);
        Root<ShapeEntity> shapeEntities = criteria.from(ShapeEntity.class);
        criteria.where(builder.equal(shapeEntities.get("shapeId"), id));
        try {
            ShapeEntity shapeEntity = session.createQuery(criteria).getSingleResult();
            return new ShapeModel(shapeEntity);
        } catch (NoResultException e) {
            return null;
        }
    }

    public ShapeModel create(int shapeId, Integer problemId, Integer level, Integer userId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ShapeModel shapeModel = new ShapeModel(shapeId, problemId, level, userId);
            int id = Integer.valueOf(String.valueOf(session.save(shapeModel.toEntity())));
            tx.commit();
            ShapeModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public ShapeModel create(ShapeModel shapeModel) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int id = Integer.valueOf(String.valueOf(session.save(shapeModel.toEntity())));
            tx.commit();
            ShapeModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public ShapeModel update(int shapeId, Integer problemId, Integer level, Integer userId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ShapeModel shapeModel = new ShapeModel(shapeId, problemId, level, userId);
            session.update(shapeModel.toEntity());
            tx.commit();
            ShapeModel result = get(shapeId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public ShapeModel update(int shapeId, ShapeModel shapeModel) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(shapeModel.toEntity());
            tx.commit();
            ShapeModel result = get(shapeId);
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
            ShapeEntity shapeEntity = new ShapeEntity();
            shapeEntity.setShapeId(id);
            session.delete(shapeEntity);
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

    public List<ShapeModel> get(SearchShapeModel searchShapeModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ShapeEntity> criteria = builder.createQuery(ShapeEntity.class);
        Root<ShapeEntity> ShapeEntities = criteria.from(ShapeEntity.class);
        try {
            List<ShapeEntity> shapeEntities = session.createQuery(criteria).getResultList();
            return Lists.transform(shapeEntities, shapeEntity -> new ShapeModel(shapeEntity));
        } catch (NoResultException e) {
            return null;
        }
    }
}

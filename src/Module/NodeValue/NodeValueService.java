package Module.NodeValue;

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
public class NodeValueService {
    private static SessionFactory factory;
    private static int currentActive;

    public NodeValueService(SessionFactory factory) {
        this.factory = factory;
    }

    public NodeValueService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        NodeValueService.factory = factory;
    }


    public NodeValueEntity get(int nodeId, int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NodeValueModel> criteria = builder.createQuery(NodeValueModel.class);
        Root<NodeValueModel> NodeValueEntities = criteria.from(NodeValueModel.class);
        criteria.where(builder.equal(NodeValueEntities.get("nodeValueId"), id));
        try {
            NodeValueModel nodeValueModel = session.createQuery(criteria).getSingleResult();
            return new NodeValueEntity(nodeValueModel);
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public NodeEntity create(int nodeValueId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            NodeEntity nodeValueEntity = new NodeEntity(nodeValueId, startX, startY, endX, endY, shapeId);
//            NodeModel nodeValueModel = nodeValueEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(nodeValueModel)));
//            tx.commit();
//            NodeEntity result = new NodeEntity(nodeValueModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return null;
//    }

    public NodeValueEntity create(int nodeId, NodeValueEntity nodeValueEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            NodeValueModel nodeValueModel = nodeValueEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(nodeValueModel)));
            tx.commit();
            NodeValueEntity result = new NodeValueEntity(nodeValueModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public NodeEntity update(int nodeValueId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            NodeEntity nodeValueEntity = new NodeEntity(nodeValueId, startX, startY, endX, endY, shapeId);
//            session.update(nodeValueEntity.toModel());
//            tx.commit();
//            NodeEntity result = get(nodeValueId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return null;
//    }

    public NodeValueEntity update(int nodeId, int nodeValueId, NodeValueEntity nodeValueEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(nodeValueEntity.toModel());
            tx.commit();
            NodeValueEntity result = get(nodeId, nodeValueId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int nodeId, int id) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            NodeValueModel nodeValueModel = new NodeValueModel();
            nodeValueModel.setId(id);
            session.delete(nodeValueModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<NodeValueEntity> get(int nodeId, SearchNodeValueModel searchNodeValueModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NodeValueModel> criteria = builder.createQuery(NodeValueModel.class);
        Root<NodeValueModel> NodeValueEntities = criteria.from(NodeValueModel.class);
        criteria.where(builder.equal(NodeValueEntities.get("nodeId"), nodeId));
        try {
            List<NodeValueModel> nodeValueEntities = session.createQuery(criteria).getResultList();
            return Lists.transform(nodeValueEntities, nodeValueEntity -> new NodeValueEntity(nodeValueEntity));
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}

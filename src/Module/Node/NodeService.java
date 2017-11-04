package Module.Node;

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
public class NodeService {
    private static SessionFactory factory;
    private static int currentActive;

    public NodeService(SessionFactory factory) {
        this.factory = factory;
    }

    public NodeService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        NodeService.factory = factory;
    }


    public NodeEntity get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NodeModel> criteria = builder.createQuery(NodeModel.class);
        Root<NodeModel> NodeEntities = criteria.from(NodeModel.class);
        criteria.where(builder.equal(NodeEntities.get("id"), id));
        try {
            NodeModel nodeModel = session.createQuery(criteria).getSingleResult();
            return new NodeEntity(nodeModel, nodeModel.getNodevaluesById());
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }

//    public NodeEntity create(int nodeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            NodeEntity nodeEntity = new NodeEntity(nodeId, startX, startY, endX, endY, shapeId);
//            NodeModel nodeModel = nodeEntity.toModel();
//            Integer.valueOf(String.valueOf(session.save(nodeModel)));
//            tx.commit();
//            NodeEntity result = new NodeEntity(nodeModel);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return null;
//    }

    public NodeEntity create(NodeEntity nodeEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            NodeModel nodeModel = nodeEntity.toModel();
            Integer.valueOf(String.valueOf(session.save(nodeModel)));
            tx.commit();
            NodeEntity result = new NodeEntity(nodeModel);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

//    public NodeEntity update(int nodeId, Double startX, Double startY, Double endX, Double endY, Integer shapeId) {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            NodeEntity nodeEntity = new NodeEntity(nodeId, startX, startY, endX, endY, shapeId);
//            session.update(nodeEntity.toModel());
//            tx.commit();
//            NodeEntity result = get(nodeId);
//            return result;
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return null;
//    }

    public NodeEntity update(int nodeId, NodeEntity nodeEntity) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(nodeEntity.toModel());
            tx.commit();
            NodeEntity result = get(nodeId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            NodeModel nodeModel = new NodeModel();
            nodeModel.setId(id);
            session.delete(nodeModel);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<NodeEntity> get(SearchNodeModel searchNodeModel) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NodeModel> criteria = builder.createQuery(NodeModel.class);
        Root<NodeModel> NodeEntities = criteria.from(NodeModel.class);
        try {
            List<NodeModel> nodeEntities = session.createQuery(criteria).getResultList();
            return Lists.transform(nodeEntities, nodeEntity -> new NodeEntity(nodeEntity));
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }
    }
}

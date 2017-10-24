package Module.Edge;

import Manager.Entity.DatabaseEntity;
import Manager.Interface.IDatabaseControllService;
import Manager.Interface.IDatabaseService;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

/**
 * Created by Son on 6/15/2017.
 */
public class EdgeService extends AbstractBinder {
    private static SessionFactory factory;
    private static int currentActive;

    public EdgeService(SessionFactory factory) {
        this.factory = factory;
    }

    public EdgeService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        EdgeService.factory = factory;
    }

    @Override
    protected void configure() {
        bind(EdgeService.class).to(EdgeService.class);
    }

    public EdgeModel get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EdgeEntity> criteria = builder.createQuery(EdgeEntity.class);
        Root<EdgeEntity> EdgeEntities = criteria.from(EdgeEntity.class);
        criteria.where(builder.equal(EdgeEntities.get("edgeId"), id));
        try {
            EdgeEntity edgeEntity = session.createQuery(criteria).getSingleResult();
            return new EdgeModel(edgeEntity);
        } catch (NoResultException e) {
            return null;
        }
    }

    public EdgeModel create(int edgeId, Double startX, Double startY, Double endX, Double endY) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            EdgeModel edgeModel = new EdgeModel(edgeId, startX, startY, endX, endY);
            int id = Integer.valueOf(String.valueOf(session.save(edgeModel.toEntity())));
            tx.commit();
            EdgeModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public EdgeModel create(EdgeModel edgeModel) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int id = Integer.valueOf(String.valueOf(session.save(edgeModel.toEntity())));
            tx.commit();
            EdgeModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public EdgeModel update(int edgeId, Double startX, Double startY, Double endX, Double endY) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            EdgeModel edgeModel = new EdgeModel(edgeId, startX, startY, endX, endY);
            session.update(edgeModel.toEntity());
            tx.commit();
            EdgeModel result = get(edgeId);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public EdgeModel update(int edgeId, EdgeModel edgeModel) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(edgeModel.toEntity());
            tx.commit();
            EdgeModel result = get(edgeId);
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
            EdgeEntity edgeEntity = new EdgeEntity();
            edgeEntity.setEdgeId(id);
            session.delete(edgeEntity);
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

//    public JSONObject get(SearchLegalRelationshipModel searchLegalRelationshipModel) {
//        Session session = factory.openSession();
//        Criteria criteria = session.createCriteria(SearchLegalRelationshipModel.class, "legalrelationship");
//        criteria = searchLegalRelationshipModel.apply(criteria);
//        List<LegalrelationshipEntity> legalrelationshipEntities = criteria.list();
//        List<LegalRelationshipModel> legalRelationshipModels = new ArrayList<>();
//        legalrelationshipEntities.forEach(x -> {
//            legalRelationshipModels.add(new LegalRelationshipModel(x));
//        });
//        JSONObject obj = new JSONObject();
//        int statusCode = 200;
//        JSONArray data = new JSONArray();
//        for (LegalRelationshipModel x : legalRelationshipModels) {
//            data.add(x.toJsonObject());
//        }
//        obj.put("status", statusCode);
//        obj.put("data", data);
//        return obj;
//    }
}

package Module.Shape;

import Manager.Entity.DatabaseEntity;
import Manager.Interface.IDatabaseControllService;
import Manager.Interface.IDatabaseService;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by Son on 6/15/2017.
 */
public class ShapeService extends AbstractBinder {
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

    @Override
    protected void configure() {
        bind(ShapeService.class).to(ShapeService.class);
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

    public ShapeModel create(int shapeId, Integer level) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ShapeModel shapeModel = new ShapeModel(shapeId, level);
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

    public ShapeModel update(int shapeId, Integer level) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ShapeModel shapeModel = new ShapeModel(shapeId, level);
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

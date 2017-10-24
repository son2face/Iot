package Module.Problem;

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
public class ProblemService extends AbstractBinder {
    private static SessionFactory factory;
    private static int currentActive;

    public ProblemService(SessionFactory factory) {
        this.factory = factory;
    }

    public ProblemService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        ProblemService.factory = factory;
    }

    @Override
    protected void configure() {
        bind(ProblemService.class).to(ProblemService.class);
    }

    public ProblemModel get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProblemEntity> criteria = builder.createQuery(ProblemEntity.class);
        Root<ProblemEntity> problemEntities = criteria.from(ProblemEntity.class);
        criteria.where(builder.equal(problemEntities.get("problemId"), id));
        try {
            ProblemEntity problemEntity = session.createQuery(criteria).getSingleResult();
            return new ProblemModel(problemEntity);
        } catch (NoResultException e) {
            return null;
        }
    }

    public ProblemModel create(int problemId, String status) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ProblemModel problemModel = new ProblemModel(problemId, status);
            int id = Integer.valueOf(String.valueOf(session.save(problemModel.toEntity())));
            tx.commit();
            ProblemModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public ProblemModel update(int problemId, String status) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ProblemModel problemModel = new ProblemModel(problemId, status);
            session.update(problemModel.toEntity());
            tx.commit();
            ProblemModel result = get(problemId);
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
            ProblemEntity problemEntity = new ProblemEntity();
            problemEntity.setProblemId(id);
            session.delete(problemEntity);
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

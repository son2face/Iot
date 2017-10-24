package Module.File;

import Manager.Entity.DatabaseEntity;
import Manager.Interface.IDatabaseControllService;
import Manager.Interface.IDatabaseService;
import Manager.Service.DatabaseControllService;
import Manager.Service.DatabaseService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;

/**
 * Created by Son on 6/15/2017.
 */
public class FileService {
    private static SessionFactory factory;
    private static int currentActive;

    public FileService(SessionFactory factory) {
        this.factory = factory;
    }

    public FileService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        FileService.factory = factory;
    }

    public FileModel get(int id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<FileEntity> criteria = builder.createQuery(FileEntity.class);
        Root<FileEntity> fileEntities = criteria.from(FileEntity.class);
        criteria.where(builder.equal(fileEntities.get("fileId"), id));
        try {
            FileEntity fileEntity = session.createQuery(criteria).getSingleResult();
            return new FileModel(fileEntity);
        } catch (NoResultException e) {
            return null;
        }
    }

    public FileModel create(int fileId, String name, byte[] data, Timestamp createdTime, String type, Timestamp expiredTime) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            FileModel fileModel = new FileModel(fileId, name, data, createdTime, type, expiredTime);
            int id = Integer.valueOf(String.valueOf(session.save(fileModel.toEntity())));
            tx.commit();
            FileModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public FileModel update(int fileId, String name, byte[] data, Timestamp createdTime, String type, Timestamp expiredTime) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            FileModel fileModel = new FileModel(fileId, name, data, createdTime, type, expiredTime);
            session.update(fileModel.toEntity());
            tx.commit();
            FileModel result = get(fileId);
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
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileId(id);
            session.delete(fileEntity);
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

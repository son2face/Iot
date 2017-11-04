package Manager.Entity;

import Manager.Interface.IDatabaseEntity;
import Manager.Model.DatabaseModel;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Son on 4/28/2017.
 */
public class DatabaseEntity implements IDatabaseEntity {
    public static int Active = 0;
    private static String fileDir;
    private static List<DatabaseModel> databaseModels;
    private static int id = 0;
    private DatabaseModel databaseModel;

    public DatabaseEntity(DatabaseModel databaseModel) {
        this.databaseModel = databaseModel;
    }

    public DatabaseEntity() {
    }

    public static void setActive(int active) {
        Active = active;
    }

    public static void loadData() {
        Scanner input = null;
        try {
            input = new Scanner(Paths.get(fileDir));
        } catch (Exception e) {
            File statText = new File(fileDir);
            try {
                statText.createNewFile();
                input = new Scanner(Paths.get(fileDir));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        databaseModels = new ArrayList<>();
        int i = 0;
        try {
            while (input.hasNext()) {
                int typeDB = input.nextInt();
                input.nextLine();
                String url = input.nextLine();
                String databaseName = input.nextLine();
                String username = input.nextLine();
                String password = input.nextLine();
                DatabaseModel databaseModel = new DatabaseModel(i, typeDB, url, databaseName, username, password);
                databaseModels.add(databaseModel);
                i++;
            }
        } catch (Exception e) {

        }
        id = databaseModels.size();
    }

    public static void saveData() throws IOException {
        File statText = new File(fileDir);
        PrintWriter writer = new PrintWriter(statText, "UTF-8");
        for (DatabaseModel x : databaseModels) {
            writer.println(x.typeDB);
            writer.println(x.url);
            writer.println(x.databaseName);
            writer.println(x.userName);
            writer.println(x.passWord);
        }
        writer.close();
    }

    public static String getFileDir() {
        return fileDir;
    }

    public static void setFileDir(String fileDir) {
        DatabaseEntity.fileDir = fileDir;
    }

    public static List<DatabaseModel> getDatabaseModels() {
        return databaseModels;
    }

    public void insert() {
        databaseModels.add(databaseModel);
        databaseModel.id = id;
        id++;
    }

    public void update() {

    }

    public void delete() {
        for (DatabaseModel x : databaseModels) {
            if (x.id == databaseModel.id) {
                databaseModels.remove(x);
                return;
            }
        }
    }

    public List<Object> select() {
        return (List<Object>) (List<?>) databaseModels;
    }

    public void insert(List<Object> data) {
        for (Object x : data) {
            DatabaseModel t = (DatabaseModel) x;
            databaseModels.add(t);
            t.id = databaseModels.size() - 1;
        }
    }

    public void create() throws IOException {
        File f = new File(fileDir);
        f.createNewFile();
    }

    public void truncate() throws IOException {
        drop();
        create();
    }

    public void drop() throws IOException {
        File f = new File(fileDir);
        f.delete();
    }

    public List<Object> select(int id) {
        for (DatabaseModel x : databaseModels) {
            if (x.id == id) {
                List<DatabaseModel> re = new ArrayList<>();
                re.add(x);
                return (List<Object>) (List<?>) re;
            }
        }
        return null;
    }
}

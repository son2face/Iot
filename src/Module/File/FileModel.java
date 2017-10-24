package Module.File;

import java.io.Serializable;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class FileModel implements Serializable {

    public int fileId;
    public String name;
    public byte[] data;
    public Timestamp createdTime;
    public String type;
    public Timestamp expiredTime;


    public FileModel() {
    }

    public FileModel(int fileId, String name, byte[] data, Timestamp createdTime, String type, Timestamp expiredTime) {
        this.fileId = fileId;
        this.name = name;
        this.data = data;
        this.createdTime = createdTime;
        this.type = type;
        this.expiredTime = expiredTime;
    }

    public FileModel(FileEntity FileEntity) {
        this.fileId = FileEntity.getFileId();
        this.name = FileEntity.getName();
        this.data = FileEntity.getData();
        this.createdTime = FileEntity.getCreatedTime();
        this.type = FileEntity.getType();
        this.expiredTime = FileEntity.getExpiredTime();
    }

    public FileEntity toEntity() {
        FileEntity FileEntity = new FileEntity();
        FileEntity.setFileId(fileId);
        FileEntity.setName(name);
        FileEntity.setData(data);
        FileEntity.setCreatedTime(createdTime);
        FileEntity.setType(type);
        FileEntity.setExpiredTime(expiredTime);
        return FileEntity;
    }
}

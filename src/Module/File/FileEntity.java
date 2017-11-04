package Module.File;

import Module.User.UserModel;
import Module.User.UserEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class FileEntity implements Serializable {

    public int fileId;
    public String name;
    public byte[] data;
    public Timestamp createdTime;
    public String type;
    public Timestamp expiredTime;
    public Integer userId;
    public UserEntity userEntity;


    public FileEntity() {
    }

    public FileEntity(int fileId, String name, byte[] data, Timestamp createdTime, String type, Timestamp expiredTime, Integer userId) {
        this.fileId = fileId;
        this.name = name;
        this.data = data;
        this.createdTime = createdTime;
        this.type = type;
        this.expiredTime = expiredTime;
        this.userId = userId;
    }

    public FileEntity(FileModel FileModel, Object... objects) {
        this.fileId = FileModel.getFileId();
        this.name = FileModel.getName();
        this.data = FileModel.getData();
        this.createdTime = FileModel.getCreatedTime();
        this.type = FileModel.getType();
        this.expiredTime = FileModel.getExpiredTime();
        this.userId = FileModel.getUserId();
        for (Object object : objects) {
            if (object instanceof UserModel) {
                this.userEntity = new UserEntity(FileModel.getUserByUserId());
            } else if (object instanceof Collection) {
            }
        }
    }

    public FileModel toEntity() {
        FileModel FileModel = new FileModel();
        FileModel.setFileId(fileId);
        FileModel.setName(name);
        FileModel.setData(data);
        FileModel.setCreatedTime(createdTime);
        FileModel.setType(type);
        FileModel.setExpiredTime(expiredTime);
        FileModel.setUserId(userId);
        if (userEntity != null) FileModel.setUserByUserId(userEntity.toEntity());
        return FileModel;
    }
}

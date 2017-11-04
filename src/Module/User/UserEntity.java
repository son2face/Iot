package Module.User;

import Module.File.FileEntity;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class UserEntity implements Serializable {
    public int userId;
    public String userName;
    public String passWord;
    private List<FileEntity> fileEntityList;

    public UserEntity() {
    }

    public UserEntity(int userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    public UserEntity(UserModel UserModel) {
        this.userId = UserModel.getUserId();
        this.userName = UserModel.getUserName();
        this.passWord = UserModel.getPassWord();
        if (UserModel.getFilesByUserId() != null)
            this.fileEntityList = UserModel.getFilesByUserId().parallelStream().map(FileEntity::new).collect(Collectors.toList());
           }

    public UserModel toEntity() {
        UserModel UserModel = new UserModel();
        UserModel.setUserId(userId);
        UserModel.setUserName(userName);
        UserModel.setPassWord(passWord);
        if (fileEntityList != null)
            UserModel.setFilesByUserId(fileEntityList.parallelStream().map(FileEntity::toEntity).collect(Collectors.toList()));
             return UserModel;
    }
}

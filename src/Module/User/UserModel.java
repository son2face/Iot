package Module.User;

import Module.Shape.ShapeEntity;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class UserModel implements Serializable {
    public int userId;
    public String userName;
    public String passWord;


    public UserModel() {
    }

    public UserModel(int userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    public UserModel(UserEntity UserEntity) {
        this.userId = UserEntity.getUserId();
        this.userName = UserEntity.getUserName();
        this.passWord = UserEntity.getPassWord();
    }

    public UserEntity toEntity() {
        UserEntity UserEntity = new UserEntity();
        UserEntity.setUserId(userId);
        UserEntity.setUserName(userName);
        UserEntity.setPassWord(passWord);
        return UserEntity;
    }
}

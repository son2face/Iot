package Module.User;

import Module.File.FileModel;
import Module.Problem.ProblemModel;
import Module.Shape.ShapeModel;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class UserModel implements Serializable {
    public int userId;
    public String userName;
    public String passWord;
    private List<FileModel> fileModelList;
    private List<ProblemModel> problemModelList;
    private List<ShapeModel> shapeModelList;

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
        this.fileModelList = UserEntity.getFilesByUserId().parallelStream().map(FileModel::new).collect(Collectors.toList());
        this.problemModelList = UserEntity.getProblemsByUserId().parallelStream().map(ProblemModel::new).collect(Collectors.toList());
        this.shapeModelList = UserEntity.getShapesByUserId().parallelStream().map(ShapeModel::new).collect(Collectors.toList());
    }

    public UserEntity toEntity() {
        UserEntity UserEntity = new UserEntity();
        UserEntity.setUserId(userId);
        UserEntity.setUserName(userName);
        UserEntity.setPassWord(passWord);
        UserEntity.setFilesByUserId(fileModelList.parallelStream().map(FileModel::toEntity).collect(Collectors.toList()));
        UserEntity.setProblemsByUserId(problemModelList.parallelStream().map(ProblemModel::toEntity).collect(Collectors.toList()));
        UserEntity.setShapesByUserId(shapeModelList.parallelStream().map(ShapeModel::toEntity).collect(Collectors.toList()));
        return UserEntity;
    }
}

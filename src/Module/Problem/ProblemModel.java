package Module.Problem;

import Module.File.FileEntity;
import Module.File.FileModel;
import Module.Point.PointEntity;
import Module.Point.PointModel;
import Module.Shape.ShapeEntity;
import Module.Shape.ShapeModel;
import Module.User.UserEntity;
import Module.User.UserModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ProblemModel implements Serializable {
    public int problemId;
    public String status;
    public Integer fileId;
    public Integer userId;
    public FileModel fileModel;
    public UserModel userModel;
    public List<PointModel> pointModelList;
    public List<ShapeModel> shapeModelList;

    public ProblemModel() {
    }

    public ProblemModel(int problemId, String status,Integer fileId,Integer userId) {
        this.problemId = problemId;
        this.status = status;
        this.fileId = fileId;
        this.userId = userId;
    }

    public ProblemModel(ProblemEntity ProblemEntity) {
        this.problemId = ProblemEntity.getProblemId();
        this.status = ProblemEntity.getStatus();
        this.fileId = ProblemEntity.getFileId();
        this.userId = ProblemEntity.getUserId();
        this.fileModel = new FileModel(ProblemEntity.getFileByFileId());
        this.userModel = new UserModel(ProblemEntity.getUserByUserId());
        this.pointModelList = ProblemEntity.getPointsByProblemId().parallelStream().map(PointModel::new).collect(Collectors.toList());
        this.shapeModelList = ProblemEntity.getShapesByProblemId().parallelStream().map(ShapeModel::new).collect(Collectors.toList());
    }

    public ProblemEntity toEntity() {
        ProblemEntity ProblemEntity = new ProblemEntity();
        ProblemEntity.setProblemId(problemId);
        ProblemEntity.setStatus(status);
        ProblemEntity.setFileId(fileId);
        ProblemEntity.setUserId(userId);
        ProblemEntity.setFileByFileId(fileModel.toEntity());
        ProblemEntity.setUserByUserId(userModel.toEntity());
        ProblemEntity.setPointsByProblemId(pointModelList.parallelStream().map(PointModel::toEntity).collect(Collectors.toList()));
        ProblemEntity.setShapesByProblemId(shapeModelList.parallelStream().map(ShapeModel::toEntity).collect(Collectors.toList()));
        return ProblemEntity;
    }
}

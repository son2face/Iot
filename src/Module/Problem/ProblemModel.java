package Module.Problem;

import Module.Point.PointEntity;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ProblemModel implements Serializable {
    public int problemId;
    public String status;


    public ProblemModel() {
    }

    public ProblemModel(int problemId, String status) {
        this.problemId = problemId;
        this.status = status;
    }

    public ProblemModel(ProblemEntity ProblemEntity) {
        this.problemId = ProblemEntity.getProblemId();
        this.status = ProblemEntity.getStatus();
    }

    public ProblemEntity toEntity() {
        ProblemEntity ProblemEntity = new ProblemEntity();
        ProblemEntity.setProblemId(problemId);
        ProblemEntity.setStatus(status);
        return ProblemEntity;
    }
}

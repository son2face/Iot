package Module.Problem;

import Module.File.FileEntity;
import Module.Point.PointEntity;
import Module.Shape.ShapeEntity;
import Module.User.UserEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "problem", schema = "intelligent", catalog = "")
public class ProblemEntity {
    private int problemId;
    private String status;
    private Integer fileId;
    private Integer userId;
    private Collection<PointEntity> pointsByProblemId;
    private FileEntity fileByFileId;
    private UserEntity userByUserId;
    private Collection<ShapeEntity> shapesByProblemId;

    @Id
    @Column(name = "problemId", nullable = false)
    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "fileId", nullable = true)
    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    @Basic
    @Column(name = "userId", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProblemEntity that = (ProblemEntity) o;

        if (problemId != that.problemId) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (fileId != null ? !fileId.equals(that.fileId) : that.fileId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = problemId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (fileId != null ? fileId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "problemByProblemId")
    public Collection<PointEntity> getPointsByProblemId() {
        return pointsByProblemId;
    }

    public void setPointsByProblemId(Collection<PointEntity> pointsByProblemId) {
        this.pointsByProblemId = pointsByProblemId;
    }

    @ManyToOne
    @JoinColumn(name = "fileId", referencedColumnName = "fileId", insertable=false, updatable=false)
    public FileEntity getFileByFileId() {
        return fileByFileId;
    }

    public void setFileByFileId(FileEntity fileByFileId) {
        this.fileByFileId = fileByFileId;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable=false, updatable=false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "problemByProblemId")
    public Collection<ShapeEntity> getShapesByProblemId() {
        return shapesByProblemId;
    }

    public void setShapesByProblemId(Collection<ShapeEntity> shapesByProblemId) {
        this.shapesByProblemId = shapesByProblemId;
    }
}

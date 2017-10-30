package Module.Shape;

import Module.Edge.EdgeEntity;
import Module.Problem.ProblemEntity;
import Module.User.UserEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "shape", schema = "intelligent", catalog = "")
public class ShapeEntity {
    private int shapeId;
    private Integer problemId;
    private Integer level;
    private Integer userId;
    private Collection<EdgeEntity> edgesByShapeId;
    private ProblemEntity problemByProblemId;
    private UserEntity userByUserId;

    @Id
    @Column(name = "shapeId", nullable = false)
    public int getShapeId() {
        return shapeId;
    }

    public void setShapeId(int shapeId) {
        this.shapeId = shapeId;
    }

    @Basic
    @Column(name = "problemId", nullable = true)
    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

        ShapeEntity that = (ShapeEntity) o;

        if (shapeId != that.shapeId) return false;
        if (problemId != null ? !problemId.equals(that.problemId) : that.problemId != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shapeId;
        result = 31 * result + (problemId != null ? problemId.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "shapeByShapeId")
    public Collection<EdgeEntity> getEdgesByShapeId() {
        return edgesByShapeId;
    }

    public void setEdgesByShapeId(Collection<EdgeEntity> edgesByShapeId) {
        this.edgesByShapeId = edgesByShapeId;
    }

    @ManyToOne
    @JoinColumn(name = "problemId", referencedColumnName = "problemId", insertable=false, updatable=false)
    public ProblemEntity getProblemByProblemId() {
        return problemByProblemId;
    }

    public void setProblemByProblemId(ProblemEntity problemByProblemId) {
        this.problemByProblemId = problemByProblemId;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable=false, updatable=false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}

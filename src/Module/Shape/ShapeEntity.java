package Module.Shape;

import Module.Problem.ProblemEntity;

import javax.persistence.*;

@Entity
@Table(name = "shape", schema = "intelligent", catalog = "")
public class ShapeEntity {
    private int shapeId;
    private Integer level;
    private ProblemEntity problemByProblemId;

    @Id
    @Column(name = "shapeId", nullable = false)
    public int getShapeId() {
        return shapeId;
    }

    public void setShapeId(int shapeId) {
        this.shapeId = shapeId;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShapeEntity that = (ShapeEntity) o;

        if (shapeId != that.shapeId) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shapeId;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "problemId", referencedColumnName = "problemId")
    public ProblemEntity getProblemByProblemId() {
        return problemByProblemId;
    }

    public void setProblemByProblemId(ProblemEntity problemByProblemId) {
        this.problemByProblemId = problemByProblemId;
    }
}

package Module.Problem;

import javax.persistence.*;

@Entity
@Table(name = "point", schema = "intelligent", catalog = "")
public class SearchProblemModel {
    private int pointId;
    private Double startX;
    private Double startY;
    private Double endX;
    private Double endY;

    @Id
    @Column(name = "pointId", nullable = false)
    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    @Basic
    @Column(name = "startX", nullable = true, precision = 0)
    public Double getStartX() {
        return startX;
    }

    public void setStartX(Double startX) {
        this.startX = startX;
    }

    @Basic
    @Column(name = "startY", nullable = true, precision = 0)
    public Double getStartY() {
        return startY;
    }

    public void setStartY(Double startY) {
        this.startY = startY;
    }

    @Basic
    @Column(name = "endX", nullable = true, precision = 0)
    public Double getEndX() {
        return endX;
    }

    public void setEndX(Double endX) {
        this.endX = endX;
    }

    @Basic
    @Column(name = "endY", nullable = true, precision = 0)
    public Double getEndY() {
        return endY;
    }

    public void setEndY(Double endY) {
        this.endY = endY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchProblemModel that = (SearchProblemModel) o;

        if (pointId != that.pointId) return false;
        if (startX != null ? !startX.equals(that.startX) : that.startX != null) return false;
        if (startY != null ? !startY.equals(that.startY) : that.startY != null) return false;
        if (endX != null ? !endX.equals(that.endX) : that.endX != null) return false;
        if (endY != null ? !endY.equals(that.endY) : that.endY != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pointId;
        result = 31 * result + (startX != null ? startX.hashCode() : 0);
        result = 31 * result + (startY != null ? startY.hashCode() : 0);
        result = 31 * result + (endX != null ? endX.hashCode() : 0);
        result = 31 * result + (endY != null ? endY.hashCode() : 0);
        return result;
    }
}

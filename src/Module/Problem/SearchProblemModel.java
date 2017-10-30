package Module.Problem;

import Module.FilterModel;

import javax.persistence.*;

@Entity
@Table(name = "point", schema = "intelligent", catalog = "")
public class SearchProblemModel extends FilterModel {
    private int pointId;
    private Double startX;
    private Double startY;
    private Double endX;
    private Double endY;
}

package Module.Point;

import Module.FilterModel;

import javax.persistence.*;

@Entity
@Table(name = "point", schema = "intelligent", catalog = "")
public class SearchPointModel extends FilterModel {
    private int pointId;
    private Double startX;
    private Double startY;
    private Double endX;

}

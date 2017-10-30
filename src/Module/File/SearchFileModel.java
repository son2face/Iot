package Module.File;

import Module.FilterModel;

import javax.persistence.*;

@Entity
@Table(name = "edge", schema = "intelligent", catalog = "")
public class SearchFileModel extends FilterModel{
    private int edgeId;
    private Double startX;
    private Double startY;
    private Double endX;
    private Double endY;

}

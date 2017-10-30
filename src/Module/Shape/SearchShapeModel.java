package Module.Shape;

import Module.FilterModel;

import javax.persistence.*;
import javax.ws.rs.QueryParam;

@Entity
@Table(name = "point", schema = "intelligent", catalog = "")
public class SearchShapeModel extends FilterModel {
    @QueryParam("shapeId")
    public int shapeId;
    @QueryParam("level")
    public Integer level;
}

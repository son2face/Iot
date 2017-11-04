package Module.NodeValue;

import javax.persistence.*;
import javax.ws.rs.QueryParam;

public class SearchNodeValueModel {
    @QueryParam("edgeId")private int edgeId;
    @QueryParam("startX") private Double startX;
    @QueryParam("startY") private Double startY;
    @QueryParam("endX")private Double endX;
    @QueryParam("endY")private Double endY;

}

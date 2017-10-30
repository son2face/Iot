package Module;

import javax.ws.rs.QueryParam;

public class FilterModel {
    @QueryParam("Skip")
    public int Skip;
    @QueryParam("Take")
    public int Take;
    @QueryParam("OrderBy")
    public String OrderBy;
    @QueryParam("OrderType")
    public String OrderType;

    public FilterModel() {

    }
}

package Module.Node;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Path("/nodes")
public class NodeController {
    @Inject
    private NodeService nodeService;

    public NodeController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<NodeEntity> get(@BeanParam SearchNodeModel searchNodeModel) {
        return nodeService.get(searchNodeModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchNodeModel searchNodeModel) {
        return 100;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{nodeId}")
    public NodeEntity getId(@PathParam("nodeId") int nodeId) {
        return nodeService.get(nodeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{nodeId}/alert")
    public String alert(@PathParam("nodeId") int nodeId) {
        return nodeService.alert(nodeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/TimeNow")
    public String getId() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return "{\"time\":\""+sdf.format(cal.getTime())+"\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{nodeId}/setAlert/{value}")
    public NodeEntity setAlert(@PathParam("nodeId") int nodeId,@PathParam("value") int value) {
        return nodeService.setAlert(nodeId,value);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("regist")
    public NodeEntity regist() {
        NodeEntity node = new NodeEntity(0, new Timestamp(System.currentTimeMillis()));
        return nodeService.create(node);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public NodeEntity create(NodeEntity nodeEntity) {
        return nodeService.create(nodeEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{nodeId}")
    public NodeEntity update(@PathParam("nodeId") int nodeId, NodeEntity nodeEntity) {
        return nodeService.update(nodeId, nodeEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{nodeId}")
    public void delete(@PathParam("nodeId") int nodeId) {
        nodeService.delete(nodeId);
    }

}
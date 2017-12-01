package Module.NodeValue;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.List;

@Path("nodes/{nodeId}/values")
public class NodeValueController {
    @Inject
    private NodeValueService nodeValueService;

    public NodeValueController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<NodeValueEntity> get(@PathParam("nodeId") int nodeId,@BeanParam SearchNodeValueModel searchNodeValueModel) {
        return nodeValueService.get(nodeId,searchNodeValueModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public Long count(@PathParam("nodeId") int nodeId,@BeanParam SearchNodeValueModel searchNodeValueModel) {
        return nodeValueService.count(nodeId,searchNodeValueModel);
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Virtualize")
    public List<NodeValueEntity> virtualize(@PathParam("nodeId") int nodeId,@BeanParam SearchNodeValueModel searchNodeValueModel) {
        return nodeValueService.getSleepScore(nodeId,searchNodeValueModel);
    }

    //
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{nodeValueId}")
//    public NodeEntity getId(@PathParam("nodeValueId") int nodeValueId) {
//        return nodeValueService.get(nodeValueId);
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{value}")
    public NodeValueEntity getId(@PathParam("nodeId") int nodeId, @PathParam("value") int value) {
        NodeValueEntity nodeValue = new NodeValueEntity(0, value, new Timestamp(System.currentTimeMillis()), nodeId);
        return nodeValueService.create(nodeId,nodeValue);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public NodeValueEntity create(@PathParam("nodeId") int nodeId,NodeValueEntity nodeValueEntity) {
        return nodeValueService.create(nodeId,nodeValueEntity);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{nodeValueId}")
    public NodeValueEntity update(@PathParam("nodeId") int nodeId,@PathParam("nodeValueId") int nodeValueId, NodeValueEntity nodeValueEntity) {
        return nodeValueService.update(nodeId,nodeValueId, nodeValueEntity);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{nodeValueId}")
    public void delete(@PathParam("nodeId") int nodeId,@PathParam("nodeValueId") int nodeValueId) {
        nodeValueService.delete(nodeId,nodeValueId);
    }

}
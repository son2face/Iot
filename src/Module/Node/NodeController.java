package Module.Node;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
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
package Module.Edge;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Edges")
public class EdgeController {
    @Inject
    private EdgeService edgeService;

    public EdgeController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<EdgeModel> get(@BeanParam SearchEdgeModel searchEdgeModel) {
        return edgeService.get(searchEdgeModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchEdgeModel searchEdgeModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public EdgeModel getId(@PathParam("edgeId") int edgeId) {
        return edgeService.get(edgeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EdgeModel create(EdgeModel edgeModel) {
        return edgeService.create(edgeModel);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public EdgeModel update(@PathParam("edgeId") int edgeId, EdgeModel edgeModel) {
        return edgeService.update(edgeId, edgeModel);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{edgeId}")
    public void delete(@PathParam("edgeId") int edgeId) {
        edgeService.delete(edgeId);
    }

}
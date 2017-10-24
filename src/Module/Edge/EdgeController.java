package Module.Edge;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Managers/Databases")
public class EdgeController {

    private EdgeService edgeService;

    public EdgeController(EdgeService edgeService) {
        this.edgeService = edgeService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public EdgeModel get(EdgeModel edgeModel) {
        return edgeService.create(edgeModel);
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
    @Path("")
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
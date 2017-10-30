package Module.Shape;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Shapes")
public class ShapeController {
    @Inject
    private ShapeService shapeService;

    public ShapeController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ShapeModel> get(@BeanParam SearchShapeModel searchShapeModel) {
        return shapeService.get(searchShapeModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchShapeModel searchShapeModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{shapeId}")
    public ShapeModel getId(@PathParam("shapeId") int shapeId) {
        return shapeService.get(shapeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ShapeModel create(ShapeModel shapeModel) {
        return shapeService.create(shapeModel);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{shapeId}")
    public ShapeModel update(@PathParam("shapeId") int shapeId, ShapeModel shapeModel) {
        return shapeService.update(shapeId, shapeModel);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{shapeId}")
    public void delete(@PathParam("shapeId") int shapeId) {
        shapeService.delete(shapeId);
    }

}
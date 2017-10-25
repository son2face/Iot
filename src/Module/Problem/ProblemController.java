package Module.Problem;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Problems")
public class ProblemController {
    @Inject
    private ProblemService problemService;

    public ProblemController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public List<ProblemModel> get(SearchProblemModel searchProblemModel) {
        return problemService.get(searchProblemModel);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{problemId}")
    public ProblemModel getId(@PathParam("problemId") int problemId) {
        return problemService.get(problemId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public ProblemModel create(ProblemModel problemModel) {
        return problemService.create(problemModel);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{problemId}")
    public ProblemModel update(@PathParam("problemId") int problemId, ProblemModel problemModel) {
        return problemService.update(problemId, problemModel);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{problemId}")
    public void delete(@PathParam("problemId") int problemId) {
        problemService.delete(problemId);
    }

}
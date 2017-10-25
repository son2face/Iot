package Module.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Users")
public class UserController {
    @Inject
    private UserService userService;

    public UserController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public List<UserModel> get(SearchUserModel searchUserModel) {
        return userService.get(searchUserModel);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public UserModel getId(@PathParam("userId") int userId) {
        return userService.get(userId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public UserModel create(UserModel userModel) {
        return userService.create(userModel);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public UserModel update(@PathParam("userId") int userId, UserModel userModel) {
        return userService.update(userId, userModel);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public void delete(@PathParam("userId") int userId) {
        userService.delete(userId);
    }

}
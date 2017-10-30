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
    public List<UserModel> get(@BeanParam SearchUserModel searchUserModel) {
        return userService.get(searchUserModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchUserModel searchUserModel) {
        return 100;
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
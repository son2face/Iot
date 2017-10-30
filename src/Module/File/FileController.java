package Module.File;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Files")
public class FileController {
    @Inject
    private FileService fileService;

    public FileController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<FileModel> get(@BeanParam SearchFileModel searchFileModel) {
        return fileService.get(searchFileModel);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/Count")
    public int count(@BeanParam SearchFileModel searchFileModel) {
        return 100;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{fileId}")
    public FileModel getId(@PathParam("fileId") int fileId) {
        return fileService.get(fileId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public FileModel create(FileModel fileModel) {
        return fileService.create(fileModel);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{fileId}")
    public FileModel update(@PathParam("fileId") int fileId, FileModel fileModel) {
        return fileService.update(fileId, fileModel);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{fileId}")
    public void delete(@PathParam("fileId") int fileId) {
        fileService.delete(fileId);
    }

}
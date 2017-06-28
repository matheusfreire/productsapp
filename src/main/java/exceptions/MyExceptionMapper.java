package exceptions;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.glassfish.grizzly.utils.Exceptions;

@Provider
public class MyExceptionMapper  implements javax.ws.rs.ext.ExceptionMapper<Exception> {
	@Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        return Response.status(500).build();
    }
}
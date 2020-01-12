package com.jax.jersey.JerseyProject.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String getParamUsingAnnotations(@MatrixParam("param") String param,
                                           @HeaderParam("customHeaderValue") String header,
                                           @CookieParam("name") String cookie) {
        return "Matrix Param: " + param + " and Header Param: " + header + " and Cookie Param: " + cookie;
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
        return "Cookies: " + httpHeaders.getCookies().toString();
    }
}

package com.jax.jersey.JerseyProject.filter;



import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_URL_PREFIX = "messages";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
            if (authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                byte[] decodedByte = Base64.getDecoder().decode(authToken);
                String decodedString = new String(decodedByte);

                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String userName = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                if ("user".equals(userName) && "password".equals(password))
                    return;

                //If not authorized
                Response unauthorizedResponse = Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("User can not access the resource.")
                        .build();
                requestContext.abortWith(unauthorizedResponse);
            }
        }
    }
}

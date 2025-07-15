package com.test.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if(exception instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Not Found", exception.getMessage()))
                    .build();
        }
        if(exception instanceof BadRequestException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Bad Request", exception.getMessage()))
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Internal server Error", exception.getMessage()))
                .build();
    }
}

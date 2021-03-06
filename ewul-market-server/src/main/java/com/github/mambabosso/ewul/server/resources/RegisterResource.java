package com.github.mambabosso.ewul.server.resources;

import com.github.mambabosso.ewul.server.model.core.user.User;
import com.github.mambabosso.ewul.server.model.request.DataMap;
import com.github.mambabosso.ewul.server.result.Result;
import com.github.mambabosso.ewul.server.service.RegisterService;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequiredArgsConstructor(onConstructor_ = @Inject)
@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterResource {

    private final RegisterService registerService;

    @POST
    @UnitOfWork
    @Path("/")
    public Response register(@Valid DataMap dataMap) {

        String username = dataMap.getString("username");
        String password = dataMap.getString("password");

        Result<User> user = registerService.register(username, password);

        return user.toResponse(200, 400);
    }

}

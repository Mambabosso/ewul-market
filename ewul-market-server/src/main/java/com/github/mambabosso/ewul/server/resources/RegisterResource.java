package com.github.mambabosso.ewul.server.resources;

import com.github.mambabosso.ewul.server.model.core.user.User;
import com.github.mambabosso.ewul.server.model.request.DataMap;
import com.github.mambabosso.ewul.server.result.Result;
import com.github.mambabosso.ewul.server.service.RegisterService;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.NonNull;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterResource {

    private final RegisterService registerService;

    @Inject
    public RegisterResource(@NonNull final RegisterService registerService) {
        this.registerService = registerService;
    }

    @POST
    @UnitOfWork
    @Path("/")
    public Response register(@Valid DataMap dataMap) {

        String username = dataMap.getString("username");
        String password = dataMap.getString("password");

        Result<User> user = registerService.register(username, password);

        if (user.isSuccess()) {
            return Response.status(200).entity(user.get().getName()).build();
        }
        return Response.status(400).entity(user).build();
    }

}
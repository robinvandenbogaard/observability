package com.example;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

public interface TodoApp {

    @POST
    @Path("create")
    void createNew();

    @POST
    @Path("complete/{tokenId}")
    void complete(@PathParam("tokenId") UUID uuid);

    @POST
    @Path("uncomplete/{tokenId}")
    void uncomplete(@PathParam("tokenId") UUID uuid);

    @GET
    @Path("todo/{tokenId}")
    TodoDTO get(@PathParam("tokenId") UUID uuid);

    @GET
    @Path("todos")
    List<TodoDTO> getAll();
}

package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logement")
public class LogementRessources {
    static LogementBusiness help = new LogementBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response
                .status(200).header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogements())
                .build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementById(@PathParam("id") int id) {
        Logement logement = help.getLogementById(id);
        if (logement != null) {
            return Response
                    .status(200).header("Access-Control-Allow-Origin", "*")
                    .entity(logement)
                    .build();
        } else {
            return Response
                    .status(404).header("Access-Control-Allow-Origin", "*")
                    .entity("Logement not found")
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean isAdded = help.addLogement(logement);
        if (isAdded) {
            return Response
                    .status(201).header("Access-Control-Allow-Origin", "*")
                    .entity("Logement added successfully")
                    .build();
        } else {
            return Response
                    .status(500).header("Access-Control-Allow-Origin", "*")
                    .entity("Failed to add logement")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("id") int id) {
        boolean isDeleted = help.deleteLogement(id);
        if (isDeleted) {
            return Response
                    .status(200).header("Access-Control-Allow-Origin", "*")
                    .entity("Logement deleted successfully")
                    .build();
        } else {
            return Response
                    .status(404).header("Access-Control-Allow-Origin", "*")
                    .entity("Logement not found")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("id") int id, Logement logement) {
        boolean isUpdated = help.updateLogement(id, logement);
        if (isUpdated) {
            return Response
                    .status(200).header("Access-Control-Allow-Origin", "*")
                    .entity("Logement updated successfully")
                    .build();
        } else {
            return Response
                    .status(404).header("Access-Control-Allow-Origin", "*")
                    .entity("Logement not found")
                    .build();
        }
    }
}
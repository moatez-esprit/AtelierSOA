package webservices;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/RendezVous")
public class RendezVousRessources {
    static RendezVousBusiness help = new RendezVousBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response
                .status(200).header("Access-Control-Allow-Origin", "*")
                .entity(help.getListeRendezVous())
                .build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rendezVous = help.getRendezVousById(id);
        if (rendezVous != null) {
            return Response
                    .status(200).header("Access-Control-Allow-Origin", "*")
                    .entity(rendezVous)
                    .build();
        } else {
            return Response
                    .status(404).header("Access-Control-Allow-Origin", "*")
                    .entity("RendezVous not found")
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        boolean isAdded = help.addRendezVous(rendezVous);
        if (isAdded) {
            return Response
                    .status(201).header("Access-Control-Allow-Origin", "*")
                    .entity("RendezVous added successfully")
                    .build();
        } else {
            return Response
                    .status(500).header("Access-Control-Allow-Origin", "*")
                    .entity("Failed to add RendezVous")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean isDeleted = help.deleteRendezVous(id);
        if (isDeleted) {
            return Response
                    .status(200).header("Access-Control-Allow-Origin", "*")
                    .entity("RendezVous deleted successfully")
                    .build();
        } else {
            return Response
                    .status(404).header("Access-Control-Allow-Origin", "*")
                    .entity("RendezVous not found")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        boolean isUpdated = help.updateRendezVous(id, rendezVous);
        if (isUpdated) {
            return Response
                    .status(200).header("Access-Control-Allow-Origin", "*")
                    .entity("RendezVous updated successfully")
                    .build();
        } else {
            return Response
                    .status(404).header("Access-Control-Allow-Origin", "*")
                    .entity("RendezVous not found")
                    .build();
        }
    }
}


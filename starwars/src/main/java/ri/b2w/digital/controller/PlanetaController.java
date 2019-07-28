/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ri.b2w.digital.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ri.b2w.digital.bean.Planeta;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ri.b2w.digital.dao.PlanetaDAO;
import ri.b2w.digital.swapi.ArgumentSwitcher;

/**
 *
 * @author Carine Henriques
 */
@Path("planetas")
public class PlanetaController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Planeta> listPlanetas() {
        try {
            PlanetaDAO dao = new PlanetaDAO();
            return dao.listar();
        } catch (Exception ex) {
            Logger.getLogger(PlanetaController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(Planeta planeta) {
        try {
            if(planeta.getNome()!=null && !planeta.getNome().equals("")){
                ArgumentSwitcher argumentSwitcher = new ArgumentSwitcher();
                JsonArray planetresults = argumentSwitcher.switcher(planeta.getNome());
                JsonObject planet = planetresults.get(0).getAsJsonObject();
                JsonArray filmsArray = planet.getAsJsonArray("films");
                planeta.setQuantidadeAparicao(filmsArray.size());
                
                PlanetaDAO dao = new PlanetaDAO();
                dao.inserir(planeta);
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.PRECONDITION_FAILED).build();
            }
        } catch (Exception ex) {
            Logger.getLogger(PlanetaController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("pesquisa/{id}/{nome}")
    public List<Planeta> pesquisar(@PathParam("id") String id, @PathParam("nome") String nome) {
        try {     
            String clausula="";
            if(id!=null && !id.equals("undefined") && !id.equals("")){
                clausula+=" OR id="+id;
            }
            if(nome!=null && !nome.equals("undefined") && !nome.equals("")){
                clausula+=" OR nm_nome='"+nome+"'";
            }
            PlanetaDAO dao = new PlanetaDAO();
            return dao.pesquisar(clausula.substring(4));
        } catch (Exception ex) {
            Logger.getLogger(PlanetaController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        try {
            PlanetaDAO dao = new PlanetaDAO();
            dao.excluir(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            Logger.getLogger(PlanetaController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}

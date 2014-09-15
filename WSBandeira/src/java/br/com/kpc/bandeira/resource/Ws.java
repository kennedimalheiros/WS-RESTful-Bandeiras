/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kpc.bandeira.resource;

import br.com.kpc.bandeira.core.Bandeira;
import br.com.kpc.bandeira.core.IRepositoryBandeira;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pcego
 * @author KennediMalheiros
 */
@Path("/wsbandeira")
public class Ws {

    private Bandeira bandeira = new Bandeira();

    private IRepositoryBandeira rpbBandeira;

    // anotation @GET define o método HTTP ao qual o recurso responde
    @GET
    // anotation @Produces define o tipo de retorno do recurso
    @Produces(MediaType.APPLICATION_JSON)
    // anotation @Path define o caminho para o recurso
    @Path("/getBandeira/{sigla}")
    public String getUrlBandeira(@PathParam("sigla") String sg) {
        try {
            rpbBandeira = (IRepositoryBandeira) ContextoInicial.getContext().lookup("java:global/WSBandeira/BandeiraDao");

            //Loading object computer
            bandeira = rpbBandeira.getBandeiraSigla(sg);
            return bandeira.getUrlBandeira();

        } catch (Exception ex) {
            System.out.println("falha ao carregar url retorno");
            return null;
        }
    }

    // anotation @GET define o método HTTP ao qual o recurso responde

    @GET
    // anotation @Produces define o tipo de retorno do recurso
    @Produces(MediaType.APPLICATION_JSON)
    // anotation @Path define o caminho para o recurso
    @Path("/getTodasBandeiras/")
    public List<Bandeira> getUrlTodasBandeiras() {
        try {

            rpbBandeira = (IRepositoryBandeira) ContextoInicial.getContext().lookup("java:global/WSBandeira/BandeiraDao");

            //Loading object computer
            return rpbBandeira.getTodasBandeiras();

        } catch (Exception ex) {
            System.out.println("falha ao carregar url retorno");
            return null;
        }
    }
}

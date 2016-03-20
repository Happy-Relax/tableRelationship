package org.glassfish.jersey.examples.helloworld;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liujia on 3/18/16.
 */
@Path("/company")
public class CompanyResource {
    @Inject
    private CompanyRepository companyRepository;


    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        List<Map> result = new ArrayList<>();
        List<Company> list = companyRepository.selectCompany();
        for(Company user : list){
            Map userBean = new HashMap();
            userBean.put("uri","/company/"+user.getCompanyId());
            userBean.put("name",user.getCompanyName());
            userBean.put("bossId", user.getBossId());
            if (user.getBossId()!=null){

                String bossUri="/boss/"+user.getBossId();
                userBean.put("boss", bossUri);
            }else {
                userBean.put("boss",null);
            }

            result.add(userBean);
        }

        return Response.status(200).entity(result).build();
    }
    @Path("/{companyId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("companyId") int companyId, @Context SecurityContext context){

        Map result = new HashMap();
        Company instance = companyRepository.selectCompanyById(companyId);
        result.put("uri", "/company/"+companyId);
        result.put("name", instance.getCompanyName());
        result.put("bossId", instance.getBossId());
        if (instance.getBossId()!=null){

            String bossUri="/boss/"+instance.getBossId();
            result.put("boss", bossUri);
        }else {
            result.put("boss",null);
        }

        return Response.status(200).entity(result).build();
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@FormParam("name") String companyJson) throws IOException {
        Map result = new HashMap();
//        Map newInstanceBean = new HashMap();
//        newInstanceBean.put("name", name);

//        Company company=new Company(null,name);
        ObjectMapper mapper = new ObjectMapper();
        Company company = mapper.readValue(companyJson,Company.class);

        companyRepository.insertCompany(company);

        result.put("uri", "/company/"+company.getCompanyId());
        result.put("name",company.getCompanyName());

        return Response.status(201).entity(result).build();
    }
    @Path("/")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("name") String companyJson) throws IOException {
        Map result = new HashMap();
//        Map newInstanceBean = new HashMap();
//        newInstanceBean.put("name", name);

//        Company company=new Company(null,name);
        ObjectMapper mapper = new ObjectMapper();
        Company company = mapper.readValue(companyJson,Company.class);

        companyRepository.updateCompany(company);

        result.put("uri", "/company/"+company.getCompanyId());
        result.put("name",company.getCompanyName());

        return Response.status(201).entity(result).build();
    }

    @Path("/{companyId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("companyId") int companyId, @Context SecurityContext context){

        Map result = new HashMap();
        Company company=new Company(companyId,"name",1);
        companyRepository.deleteCompanyById(company);
        result.put("uri", "/company/"+companyId);
        result.put("companyName", company.getCompanyName());
        result.put("companyId", company.getCompanyId());
        result.put("bossId", company.getBossId());
        return Response.status(200).entity(result).build();
    }

}

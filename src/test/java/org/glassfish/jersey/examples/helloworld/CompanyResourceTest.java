package org.glassfish.jersey.examples.helloworld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by liujia on 3/18/16.
 */
public class CompanyResourceTest extends TestBase{
    private String basePath = "/company";
    private Company firstCompany = mock(Company.class);
    private Company secondCompany = mock(Company.class);
    private Company newCompany = mock(Company.class);


    @Override
    public void setUp() throws Exception {
        when(companyRepository.selectCompany()).thenReturn(Arrays.asList(firstCompany, secondCompany));
        when(companyRepository.selectCompanyById(1)).thenReturn(firstCompany);
        when(companyRepository.selectCompanyById(2)).thenReturn(secondCompany);

        when(firstCompany.getBossId()).thenReturn(1);
        when(firstCompany.getCompanyName()).thenReturn("NameOne");
        when(firstCompany.getCompanyId()).thenReturn(1);

        when(secondCompany.getBossId()).thenReturn(null);
        when(secondCompany.getCompanyName()).thenReturn("NameTwo");
        when(secondCompany.getCompanyId()).thenReturn(2);
        when(secondCompany.getBoss()).thenReturn(null);
//
//        when(newUser.getId()).thenReturn(3);
//        when(newUser.getName()).thenReturn("James");
//        when(usersRepository.newUser(anyString())).thenReturn(mock(User.class));
//
//        when(usersRepository.getUserById(1)).thenReturn(firstUser);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                ((Company)arguments[0]).setCompanyId(4);
                return null;
            }
        }).when(companyRepository).insertCompany(anyObject());
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                ((Company)arguments[0]).setCompanyId(null);
                ((Company)arguments[0]).setCompanyName(null);
                ((Company)arguments[0]).setBossId(null);
                ((Company)arguments[0]).setBoss(null);
                return null;
            }
        }).when(companyRepository).deleteCompanyById(anyObject());
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                ((Company)arguments[0]).setCompanyName("update");

                return null;
            }
        }).when(companyRepository).updateCompany(anyObject());
        super.setUp();
    }


    @Test
    public void should_create_one_company() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(new Company(null,"newCompany",null));
        Form formData = new Form();
        formData.param("name",json);

        Response response = target(basePath).request().post(Entity.entity(formData, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        assertThat(response.getStatus(), is(201));

        Map user = response.readEntity(Map.class);

        assertThat((String) user.get("uri"), is(basePath+"/4"));
        assertThat((String) user.get("name"), is("newCompany"));
        assertThat((String) user.get("bossId"), is(nullValue()));
    }

    @Test
    public void should_update_a_company() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(new Company(1,null,null));
        Form formData = new Form();
        formData.param("name",json);

        Response response = target(basePath).request().put(Entity.entity(formData, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        assertThat(response.getStatus(), is(201));

        Map user = response.readEntity(Map.class);

        assertThat((String) user.get("name"), is("update"));
    }

    @Test
    public void should_list_all_companys_and_with_Boss(){
        Response response = target(basePath).request().get();

        assertThat(response.getStatus(), is(200));

        List<Map> result = response.readEntity(List.class);

        assertThat(result.size(), is(2));

        Map user = result.get(0);

        assertThat((String) user.get("uri"), is(basePath+"/"+firstCompany.getCompanyId()));
        assertThat((String) user.get("name"), is(firstCompany.getCompanyName()));
        assertThat((String) user.get("boss"), is("/boss/1"));
        assertThat((String) result.get(1).get("boss"), is(nullValue()));
    }

    @Test
    public void should_get_company_by_Id_and_without_Boss(){
        Response response = target(basePath+"/2").request().get();

        assertThat(response.getStatus(), is(200));

        Map user = response.readEntity(Map.class);

        assertThat((String) user.get("uri"), is(basePath+"/2"));
        assertThat((String) user.get("name"), is("NameTwo"));
        assertThat((String) user.get("boss"), is(nullValue()));
        assertThat((String) user.get("bossId"), is(nullValue()));
    }
    @Test
    public void should_get_company_by_Id_and_with_Boss_uri(){
        Response response = target(basePath+"/1").request().get();

        assertThat(response.getStatus(), is(200));

        Map user = response.readEntity(Map.class);

        assertThat((String) user.get("uri"), is(basePath+"/1"));
        assertThat((String) user.get("name"), is("NameOne"));
        assertThat((String) user.get("boss"), is("/boss/1"));
        assertThat((String) user.get("bossId"), is(1));
    }

    @Test
    public void should_delete_company_by_Id(){
        Response response = target(basePath+"/1").request().delete();

        assertThat(response.getStatus(), is(200));

        Map user = response.readEntity(Map.class);

        assertThat((String) user.get("uri"), is(basePath+"/1"));
        assertThat((String) user.get("companyId"), is(nullValue()));
        assertThat((String) user.get("companyName"), is(nullValue()));
        assertThat((String) user.get("boss"), is(nullValue()));
    }




}

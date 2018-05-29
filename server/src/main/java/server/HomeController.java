package server;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.Map;

@Controller("/")
public class HomeController {

    final CompanyClient companyClient;

    public HomeController(CompanyClient companyClient) {
        this.companyClient = companyClient;
    }

    @Get("/companies")
    public Map companies() {
        return companyClient.getCompanies();
    }


    @Post("/companies")
    public HttpResponse<Map> save(String name) {
        return HttpResponse.created(companyClient.saveCompany(name));
    }


    @Delete("/companies/{id}")
    public HttpResponse delete(long id) {
        return companyClient.delete(id);
    }
}

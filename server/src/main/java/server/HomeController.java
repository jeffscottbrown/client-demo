package server;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

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
}

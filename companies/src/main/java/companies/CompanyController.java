package companies;


import companies.model.Company;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.runtime.server.EmbeddedServer;

@Controller("/company")
class CompanyController {

    final CustomerRepository companyService;
    final EmbeddedServer embeddedServer;

    public CompanyController(CustomerRepository companyService, EmbeddedServer embeddedServer) {
        this.companyService = companyService;
        this.embeddedServer = embeddedServer;
    }

    @Get("/")
    public HttpResponse<ListResponse> index() {
        Iterable<Company> companies = companyService.listOrderByName();
        ListResponse response = new ListResponse(companies, embeddedServer.getHost(), embeddedServer.getPort());
        return HttpResponse.ok(response);
    }

    @Post("/")
    public HttpResponse<Company> save(String name) {
        return HttpResponse.created(companyService.persist(name));
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        if (!companyService.existsById(id)) {
            return HttpResponse.notFound();
        }
        companyService.deleteById(id);
        return HttpResponse.noContent();
    }
}


package companies

import companies.model.Company
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.runtime.server.EmbeddedServer

@Controller('/company')
class CompanyController {

    final CompanyService companyService
    final EmbeddedServer embeddedServer

    CompanyController(CompanyService companyService, EmbeddedServer embeddedServer) {
        this.companyService = companyService
        this.embeddedServer = embeddedServer
    }

    @Get('/')
    HttpResponse<ListResponse> index() {
        def companies = companyService.list()
        def response = new ListResponse(companies: companies, hostName: embeddedServer.host, hostPort: embeddedServer.port)
        HttpResponse.ok(response)
    }
}

class ListResponse {
    List<Company> companies
    int hostPort
    String hostName
}
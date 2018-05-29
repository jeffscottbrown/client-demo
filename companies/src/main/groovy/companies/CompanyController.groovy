package companies

import companies.model.Company
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
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


    @Post('/')
    HttpResponse<Company> save(String name) {
        HttpResponse.created(companyService.save(name))
    }

    @Delete('/{id}')
    HttpResponse delete(long id) {
        def company = companyService.get(id)
        if(!company) {
            return HttpResponse.notFound()
        }
        companyService.delete(company.id)
        HttpResponse.noContent()
    }
}

class ListResponse {
    List<Company> companies
    int hostPort
    String hostName
}
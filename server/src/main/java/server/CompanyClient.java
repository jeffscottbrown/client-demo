package server;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.Client;

import java.util.Map;

@Client("companies")
public interface CompanyClient {

    @Get("/company")
    Map getCompanies();
}

package server;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;

import java.util.Map;

@Client("companies")
@Retryable(delay = "500ms")
public interface CompanyClient {

    @Get("/company")
    Map getCompanies();

    @Post("/company")
    Map saveCompany(String name);

    @Delete("/company/{id}")
    HttpResponse delete(long id);
}

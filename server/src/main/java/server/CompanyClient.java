package server;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;

import java.util.Map;

@Client(value = "companies", path = "/company")
@Retryable(delay = "500ms")
public interface CompanyClient {

    @Get("/")
    Map getCompanies();

    @Post("/")
    Map saveCompany(String name);

    @Delete("/{id}")
    HttpResponse delete(long id);
}

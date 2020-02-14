package companies;

import companies.model.Company;
import io.micronaut.core.annotation.Introspected;

@Introspected
class ListResponse {
    private final Iterable<Company> companies;
    private final String hostName;
    private final int hostPort;

    public ListResponse(Iterable<Company> companies, String host, int port) {
        this.companies = companies;
        this.hostName = host;
        this.hostPort = port;
    }

    public Iterable<Company> getCompanies() {
        return companies;
    }

    public String getHostName() {
        return hostName;
    }

    public int getHostPort() {
        return hostPort;
    }
}

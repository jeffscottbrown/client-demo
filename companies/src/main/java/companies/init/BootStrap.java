package companies.init;

import companies.CustomerRepository;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;

public class BootStrap {
    final CustomerRepository companyService;

    public BootStrap(CustomerRepository companyService) {
        this.companyService = companyService;
    }

    @EventListener
    void onStartup(ServerStartupEvent event) {
        if (companyService.count() == 0) {
            companyService.persist("OCI");
            companyService.persist("Moog");
            companyService.persist("Tesla");
        }
    }
}

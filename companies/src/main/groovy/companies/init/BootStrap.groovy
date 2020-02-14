package companies.init

import companies.CompanyService
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent

import javax.inject.Singleton

@Singleton
class BootStrap {

    final CompanyService companyService

    BootStrap(CompanyService companyService) {
        this.companyService = companyService
    }

    @EventListener
    void onStartup(ServerStartupEvent event) {
        if (!companyService.count()) {
            companyService.save 'OCI'
            companyService.save 'Moog'
            companyService.save 'Tesla'
        }
    }
}

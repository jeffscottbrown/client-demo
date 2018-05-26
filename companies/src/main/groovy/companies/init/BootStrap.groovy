package companies.init

import companies.CompanyService
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent

import javax.inject.Singleton

@Singleton
class BootStrap implements ApplicationEventListener<ServerStartupEvent> {

    final CompanyService companyService

    BootStrap(CompanyService companyService) {
        this.companyService = companyService
    }

    @Override
    void onApplicationEvent(ServerStartupEvent event) {
        if(!companyService.count()) {
            companyService.save 'OCI'
            companyService.save 'Moog'
            companyService.save 'Tesla'
        }
    }
}

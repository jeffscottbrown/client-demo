package companies

import companies.model.Company
import grails.gorm.services.Service

@Service(Company)
interface CompanyService {
    List<Company> list()
    Company save(String name)
    int count()
}

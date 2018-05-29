package companies

import companies.model.Company
import grails.gorm.services.Service

@Service(Company)
interface CompanyService {
    List<Company> list()
    Company save(String name)
    int count()
    void delete(long id)
    Company get(long id)
}

package companies.model

import grails.gorm.annotation.Entity

@Entity
class Company {
    String name

    static constraints = {
        blank: false
    }
}

package oldgrails

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Product)
class ProductTest extends Specification {


    def "test of the persistence"() {
        setup:
        new Product(productName: "Refregerator", productDescription: "Can fridge smth").save(flush: true)
        expect:
        assertEquals 1, Product.list().size()
    }
}

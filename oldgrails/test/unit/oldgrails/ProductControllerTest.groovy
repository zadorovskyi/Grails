package oldgrails

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ProductController)
class ProductControllerTest extends Specification {

    def "test showProduct"() {
        def service = mockFor(ProductService)
        strictControl.demandExplicit.show(1) { Long id  }
    }

    void "test save method"() {
        when:
        request.method = "POST"
        then:
        controller.save(_)
    }

    def "DeleteProduct"() {
    }

    def "Update"() {
    }
}

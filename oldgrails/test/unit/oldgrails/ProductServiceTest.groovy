package oldgrails

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ProductService)
@Mock(Product)
class ProductServiceTest extends Specification {

    final productName = "testName"
    final productDescription = "testDescription"
    int notValidId = 10
    Product product

    def setup() {
        product = new Product(productName: productName, productDescription: productDescription).save(flush: true)
        mockForConstraintsTests(ProductCommand)
    }

    def cleanup() {
    }

    def "test showMentod with valid data"() {
        when:
        def result = service.show(product.id)
        then:
        result.productName == productName
        result.productDescription == productDescription
    }

    def "test showMentod with not existing id"() {
        when:
        def result = service.show(product.id + notValidId)
        then:
        result == null

    }

    def "test save method with valid data"() {
        int count = Product.list().size()
        def command = new ProductCommand()
        command.productName = productName + "saveMethod"
        command.productDescription = productDescription
        command.id = count + 1
        when:
        service.save(command)
        then:
        Product.get(command.id) != null
        Product.count > count
    }

    def "test save method with not valid data"() {
        int count = Product.list().size()
        def command = new ProductCommand()
        command.productName = productName
        command.productDescription = productDescription
        command.id = count + 1
        when:
        service.save(command)
        then:
        Product.get(command.id) == null
    }

    def "test deleteProduct method with valid data"() {
        int count = Product.count
        when:
        service.deleteProduct(product.id)
        then:
        Product.list().contains(product) == false
        count > Product.count
    }

    def "test deleteProduct method with not existing id"() {
        int count = Product.count
        when:
        def returnValue = service.deleteProduct(count + notValidId)
        then:
        count == Product.count
        returnValue == null
    }

    def "test updateProduct method with valid data"() {
        int count = Product.count
        def command = new ProductCommand()
        command.productName = productName + "update"
        command.productDescription = productDescription + "update"
        command.id = count
        def description = Product.get(count).productName
        when:
        service.updateProduct(command)
        then:
        !Product.get(command.id).productName.equals(description)
        Product.count == count
    }

    def "test updateProduct method with not existing id"() {
        def command = new ProductCommand()
        command.productName = productName + "update"
        command.productDescription = productDescription + "update"
        command.id = Product.count + notValidId
        when:
        service.updateProduct(command)
        then:
        NoSuchIndexException ex = thrown()

    }
}
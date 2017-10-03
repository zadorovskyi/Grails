package oldgrails

import grails.validation.Validateable
import org.apache.commons.lang.NullArgumentException

class ProductController {

    static responseFormats = ['json']
    ProductService productService


    def index() {
        respond Product.list()
    }

    def showProduct() {
        def product = productService.show(params)
        if (product == null) {
            respond status: 404
        }
        respond product
    }

    def save(ProductCommand command) {
        respond productService.save(command)
    }

    def deleteProduct() {
        def product = productService.deleteProduct(params)
        if (product == null) {
            respond status: 404
        }
        respond product
    }

    def update(ProductCommand command) {
        def product
        try {
            product = productService.updateProduct(command)
        } catch (NullArgumentException ex) {
            respond status: 404
        }
        respond product
    }

}

class ProductCommand {
    Long id
    String productName
    String productDescription

    static constraints = {
        importFrom(Product.class)

    }
}

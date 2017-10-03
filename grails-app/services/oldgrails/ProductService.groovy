package oldgrails

import grails.transaction.Transactional
import org.apache.commons.lang.NullArgumentException
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap

class ProductService {

    def show(GrailsParameterMap params) {
        def product = Product.get(params.id)
        if (product == null) {
            return
        } else {
            return product
        }
    }

    def save(ProductCommand command) {
        def product = new Product()
        product.properties = command
        product.validate()
        if (!product.hasErrors()) {
            return product.save()
        } else {
            return product.errors
        }

    }

    def deleteProduct(GrailsParameterMap params) {
        def product = Product.get(params.id)
        if (product == null) {
            return
        } else {
            product.delete(flush: true)
            return product
        }
    }

    def updateProduct(ProductCommand command) {
        def existingProduct = Product.get(command.id)
        if (existingProduct == null) {
            throw new NullArgumentException()
        } else {
            existingProduct.properties = command
            existingProduct.validate()
            if (!existingProduct.hasErrors()) {
                existingProduct.save(flush: true)
                return existingProduct
            } else {
                return existingProduct.errors
            }
        }
    }

}
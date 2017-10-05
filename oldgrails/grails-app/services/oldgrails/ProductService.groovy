package oldgrails

import grails.transaction.Transactional
import org.apache.commons.lang.NullArgumentException
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap

class ProductService {
/**
 * displays domain product with requested id from database.
 * @param params that have been received from request
 * @return this method returns product from database. If
 *         id is not valid null will be returned
 */
    def show(def id) {
        Product.get(id)
    }
/**
 * Saves domain product into database.
 * @param command Product that bind data from request
 * @return this method returns product that have been saved into data base. If
 *         command Product contains not valid data all errors will be return
 */
    def save(ProductCommand command) {
        Product product = new Product()
        product.properties = command
        product.validate()
        if (!product.hasErrors()) {
            product.save()
        } else {
            product.errors
        }

    }
/**
 * Deletes domain product with specified id from database.
 * @param params that have been received from request
 * @return this method returns product that have been deleted from database. If
 *         id is not valid null wiil be returned
 */
    def deleteProduct(def id) {
        def product = Product.get(id)
        if (product != null) {
            product.delete(flush: true)
            return product
        }
    }
/**
 * Update domain product with specified id in database.
 * @param command Product that bind data from request
 * @return this method returns product that have been updated in database. If
 *         params contains not valid data all errors will be returned. If there is no object with requested id an exception will be thrown
 */
    def updateProduct(ProductCommand command) throws NoSuchIndexException {
        def existingProduct = Product.get(command.id)
        if (existingProduct == null) {
            throw new NoSuchIndexException()
        } else {
            existingProduct.properties = command
            existingProduct.validate()
            if (!existingProduct.hasErrors()) {
                existingProduct.save(flush: true)
            } else {
                existingProduct.errors
            }
        }
    }

}
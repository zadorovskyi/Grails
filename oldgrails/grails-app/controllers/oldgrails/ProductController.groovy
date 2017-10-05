package oldgrails

class ProductController {

    static responseFormats = ['json']
    ProductService productService

/**
 *
 * @return list of all products in database
 */
    def index() {
        respond Product.list()
    }
/**
 *
 * @return return product from database with specified params
 */
    def showProduct() {
        def product = productService.show(params.id)
        if (product == null) {
            respond status: 404
        }
        respond product
    }
/**
 *
 * @param command product that bind data from request
 * @return return saved product or response with errors
 */
    def save(ProductCommand command) {
        respond productService.save(command)
    }
/**
 *
 * @return return deleted product or response status 404 if there is no object with such params in database
 */
    def deleteProduct() {
        def product = productService.deleteProduct(params.id)
        if (product == null) {
            respond status: 404
        }
        respond product
    }
/**
 *
 * @param command product that bind data from request
 * @return return updated object from database of errors
 */
    def update(ProductCommand command) {
        try {
            respond productService.updateProduct(command)
        } catch (NoSuchIndexException ex) {
            respond status: 500
        }
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

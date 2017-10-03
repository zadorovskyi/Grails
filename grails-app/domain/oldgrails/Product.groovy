package oldgrails

class Product {
    String productName
    String productDescription

    static constraints = {
        productName unique: true, nullable: false
        productDescription nullable: false

    }
}


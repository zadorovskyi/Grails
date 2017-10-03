import oldgrails.Product

class BootStrap {

    def init = { servletContext ->
        new Product(productName: "Refregerator", productDescription: "Can fridge smth").save()
        new Product(productName: "Mixer", productDescription: "Can mix smth").save()
        new Product(productName: "DishWasher", productDescription: "Can wash smth").save()

    }
    def destroy = {
    }
}

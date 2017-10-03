class UrlMappings {

    static mappings = {

        "/"(view: "/index")
        "500"(view: '/error')
        "/products"(controller: "product", action: "index", method: "GET")
        "/products/$id"(controller: "product", action: "showProduct", method: "GET")
        "/products/$id"(controller: "product", action: "deleteProduct", method: "DELETE")
        "/products/$id"(controller: "product", action: "update", method: "POST")
        "/products"(controller: "product", action: "save", method: "POST")
    }
}

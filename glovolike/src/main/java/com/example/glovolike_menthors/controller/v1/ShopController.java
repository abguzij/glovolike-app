package com.example.glovolike_menthors.controller.v1;

import com.example.glovolike_menthors.entity.Product;
import com.example.glovolike_menthors.entity.Shop;
import com.example.glovolike_menthors.service.OrderService;
import com.example.glovolike_menthors.service.ProductService;
import com.example.glovolike_menthors.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/shops")
@Tag(name = "Shop Controller",
        description = "End-pont'ы, предоставляемые клиентскому приложению " +
        "для формирования запроса, отправляемого в Order Controller")
public class ShopController {
    private final ShopService shopService;
    private final ProductService productService;
    private final OrderService orderService;

    public ShopController(ShopService shopService, ProductService productService, OrderService orderService) {
        this.shopService = shopService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Operation(summary = "Регистрация нового магазина", description = "Регистрирует магазин в системе. " +
            "Возможно зарегистрировать несколько магазинов с одинаковым именем по оному и тому же адресу")
    @PostMapping(value = "/new")
    public String registerShop(String title, Integer coordX, Integer coordY){
        this.shopService.addNewShop(title, coordX, coordY);
        return "OK";
    }

    @Operation(summary = "Получение списка магазинов с их ID", description = "Предоставляет список " +
            "магазинов зарегистрированных в системе. " +
            "Если еще ни одного магазина не было зарегистрировано, то в теле возвращает соответствующее сообщение и" +
            " Http-статус BAD REQUEST")
    @GetMapping
    public Set<Shop> getAllShops(){
        return this.shopService.getAllShops();
    }

    @Operation(summary = "Получение списка товаров (и их ID) по ID магазина",
            description = "Предоставляет список товаров магазина. " +
            "Если еще ни одного товара не было зарегистрировано в магазине или количество всех товаров равно 0, " +
            "то в теле возвращает соответствующее сообщение и Http-статус BAD REQUEST")
    @GetMapping(value = "/{shopId}/products")
    public List<Product> getAllProductsInShop(@PathVariable Integer shopId){
        return productService.getProductsListByIds(
                orderService.getIdsOfProductsInShop(shopId)
        );
    }

    @Operation(summary = "Обновление количества товара в магазине по ID магазина и ID товара",
            description = "Обновляет количество товара в магазине. " +
                    "Если в магазине не было зарегистрировано товара с таким ID, " +
                    "то в теле возвращает соответствующее сообщение и Http-статус BAD REQUEST")
    @PutMapping(value = "/{shopId}/products/{productId}/update")
    public String updateProductInShop(
            @PathVariable Integer shopId,
            @PathVariable Integer productId,
            Integer productCount
    ){
        this.orderService.updateProductCount(shopId, productId, productCount);
        return "OK";
    }

    @Operation(summary = "Получение количества товара в магазине по ID магазина и ID товара",
            description = "Предоставляет количество товара в магазине. " +
                    "Если в магазине не было зарегистрировано товара с таким ID, " +
                    "то в теле возвращает соответствующее сообщение и Http-статус BAD REQUEST")
    @GetMapping(value = "/{shopId}/products/{productId}/count")
    public Integer getProductCount(@PathVariable Integer shopId, @PathVariable Integer productId){
        return this.orderService.getProductCount(shopId, productId);
    }

    @Operation(summary = "Добавление нового товара в магазин по ID магазина",
            description = "Добавление нового товара в магазин. " +
                    "Если магазина с таким ID еще не было зарегистрировано товара с таким ID, " +
                    "то в теле возвращает соответствующее сообщение и Http-статус BAD REQUEST")
    @PostMapping(value = "/{shopId}/products/new")
    public String addNewProductToShop(@PathVariable Integer shopId, String productTitle, Integer price){
        this.shopService.addNewProduct(shopId, productTitle, price);
        return "OK";
    }
}

package com.example.glovolike_menthors.controller.v1;

import com.example.glovolike_menthors.entity.Coords;
import com.example.glovolike_menthors.entity.Courier;
import com.example.glovolike_menthors.entity.Shop;
import com.example.glovolike_menthors.service.CourierProviderService;
import com.example.glovolike_menthors.service.OrderService;
import com.example.glovolike_menthors.service.ProductService;
import com.example.glovolike_menthors.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
@Tag(name = "Order Controller",
        description = "End-pont'ы, предоставляемые клиентскому приложению " +
                "для обработки запроса, содержащего информацию о заказе")
public class OrderController {
    private final ShopService shopService;
    private final ProductService productService;
    private final OrderService orderService;
    private final CourierProviderService courierProviderService;

    public OrderController(ShopService shopService, ProductService productService, OrderService orderService, CourierProviderService courierProviderService) {
        this.shopService = shopService;
        this.productService = productService;
        this.orderService = orderService;
        this.courierProviderService = courierProviderService;
    }

    @Operation(summary = "Обрабатывает информацию о заказе от пользователя",
            description = "Обработка информации о заказе от пользователя " +
                    "и обновление информации о курьерах и товаре в магазине." +
                    "\nИформация от клиента:" +
                    "\n(1. [shopId] -- Из какого магазина заказывается товар)" +
                    "\n(2. [productId] -- Какой товар заказывается)" +
                    "\n(3. [productValue] -- Какое количество товара заказывается)" +
                    "\n(4-5. [clientCoordX, clientCoordY] -- Координаты клиента)" +
                    "\nОбновляет координаты курьера координатами курьера" +
                    " и вычитает со склада необходимое количество товара." +
                    "Если количество товара в заказе превышает количество товара на складе магазина, " +
                    "то в теле возвращает соответствующее сообщение и Http-статус BAD REQUEST")
    @PostMapping(value = "/new")
    public String makeOrder(Integer shopId,
                            Integer productId,
                            Integer productValue,
                            Integer clientCoordX,
                            Integer clientCoordY){
        Coords clientCoords = new Coords(clientCoordX, clientCoordY);
        Shop shop = shopService.getShopById(shopId);
        Courier courierForDelivery = courierProviderService.getCourierForDelivery(
                shop.getCoords(),
                clientCoords
        );

        courierForDelivery.updateMyCoords(clientCoords);
        this.orderService.sellProductOfValue(shopId, productId, productValue);

        return String.format("A courier has moved to you from the \"%s\" store with %dx %s: " +
                "%s courier will be in %.2f",
                shop.getTitle(),
                productValue,
                productService.getProductById(productId).getTitle(),
                courierForDelivery.getFullName(),
                courierForDelivery.calculateDeliveryTime(shop.getCoords(), clientCoords));
    }
}

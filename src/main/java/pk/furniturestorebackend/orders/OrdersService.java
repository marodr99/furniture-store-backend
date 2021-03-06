package pk.furniturestorebackend.orders;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.furniturestorebackend.database.furniture.Furniture;
import pk.furniturestorebackend.database.furniture.FurnitureRepository;
import pk.furniturestorebackend.database.furniture.FurnitureType;
import pk.furniturestorebackend.database.orders.OrderEntity;
import pk.furniturestorebackend.database.orders.OrdersRepository;
import pk.furniturestorebackend.exceptions.NotFoundException;
import pk.furniturestorebackend.security.SecurityConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final FurnitureRepository furnitureRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, FurnitureRepository furnitureRepository) {
        this.ordersRepository = ordersRepository;
        this.furnitureRepository = furnitureRepository;
    }

    @Transactional
    public void createOrder(PlaceOrderRequest orderRequest) {
        Integer furnitureId = orderRequest.getFurnitureId();
        Furniture furniture = furnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new NotFoundException("Furniture with id " + furnitureId + " not found"));
        Integer furnitureStock = getFurnitureStock(furnitureId, furniture.getFurnitureType());
        if (furnitureStock <= 0)
            throw new IllegalArgumentException("This furniture is out of stock");
        OrderEntity orderEntity = createOrderEntity(orderRequest, furniture);
        ordersRepository.save(orderEntity);

    }

    public List<Order> getOrders() {
        return ordersRepository.findAllByEmailOrderByDateDesc(SecurityConfig.getPrincipal());
    }

    private Integer getFurnitureStock(Integer furnitureId, FurnitureType furnitureType) {
        if (furnitureType.equals(FurnitureType.CHAIRS))
            return furnitureRepository.getStockForChair(furnitureId);
        if (furnitureType.equals(FurnitureType.WARDROBES))
            return furnitureRepository.getStockForWardrobe(furnitureId);
        return 0;
    }

    private OrderEntity createOrderEntity(PlaceOrderRequest orderRequest, Furniture furniture) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderRequest, orderEntity);
        orderEntity.setDate(LocalDate.now());
        orderEntity.setFurniture(furniture);
        orderEntity.setEmail(SecurityConfig.getPrincipal());
        return orderEntity;
    }

    public SingleOrder getOrder(Integer id) {
        return ordersRepository.findByOrderIdAndEmail(id, SecurityConfig.getPrincipal());
    }

    public List<FullOrderInfo> getAllOrders() {
        return ordersRepository.findAll().stream().map(this::createWithFullInfo).collect(Collectors.toList());
    }

    private FullOrderInfo createWithFullInfo(OrderEntity orderEntity) {
        FullOrderInfo fullOrderInfo = new FullOrderInfo();
        BeanUtils.copyProperties(orderEntity, fullOrderInfo);
        BeanUtils.copyProperties(orderEntity.getFurniture(), fullOrderInfo);
        fullOrderInfo.setFurnitureId(orderEntity.getFurniture().getId());
        return fullOrderInfo;
    }

    @Transactional
    public void modifyOrder(Integer id, FullOrderInfo fullOrderInfo) {
        OrderEntity orderEntity = ordersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order with id " + id + " not found"));
        BeanUtils.copyProperties(fullOrderInfo, orderEntity);
    }

    @Transactional
    public void deleteOrder(Integer id) {
        Optional<OrderEntity> order = ordersRepository.findById(id);
        order.ifPresent(ordersRepository::delete);
    }
}

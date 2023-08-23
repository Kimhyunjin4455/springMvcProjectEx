package hello.itemservice.domain.item;

import lombok.Data;

@Data // 핵심 도메인에는 적합하지 않음(예측 못한 상황 발생 가능성), @Getter/@Setter 사용
public class Item {
    private Long id;
    private String itemName;
    private Integer price; // Integer 쓴 이유는 price가 안들어 갈 때도 있다고 가정했기 때문
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

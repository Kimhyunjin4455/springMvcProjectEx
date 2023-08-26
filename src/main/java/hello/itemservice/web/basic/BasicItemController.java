package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final 붙은 변수 베이스로 생성자 만듬
public class BasicItemController {
    /**
     * 생성자 주입, 스프링에서 생성자가 1개일 때는 @Autowired 생략 가능
    private final ItemRepository itemRepository;
    @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    */
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){ // 아이템 목록 출력
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items); // 조회해서 넣은 것
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){ // ??? 여긴 왜 Long아니고 long인지?
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){ // itemName은 addForm.html의 <input>의 name 속성 값, Integer나 int나 아무 타입 가능
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item",item); // 저장하고 저장된 결과

        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item,
                       Model model){ // itemName은 addForm.html의 <input>의 name 속성 값, Integer나 int나 아무 타입 가능
        // @ModelAttribute: Item 객체 생성 + 요청 파라미터의 값으로 Item객체의 프로퍼티 찾음 + 해당 프로퍼티의 setter 호출해서 파라미터의 값을 입력

        itemRepository.save(item);

        model.addAttribute("item",item); // 저장하고 저장된 결과

        return "basic/item";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){ // 테스트 목적
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}

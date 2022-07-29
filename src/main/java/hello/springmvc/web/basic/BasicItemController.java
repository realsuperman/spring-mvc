package hello.springmvc.web.basic;

import hello.springmvc.domain.item.Item;
import hello.springmvc.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "/basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "/basic/addForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Item item){
        itemRepository.save(item);
        //model.addAttribute("item",item);
        /* 만약에 ModelAttribute에 이름을 달면 자동으로 model에 item객체를 item 이름으로 달아줌(파라미터에 Model도 안받아도 됨)
           사실 ModelAttribute의 이름 조차도 떼면 됨 (한마디로 ModelAttribute만 있으면 알아서 자동으로 model 객체에 클래스명의 소문자를 이름으로 하고 그걸 값으로 해서 넣어줌)
         */
        return "redirect:/basic/items/"+item.getId();
        /*
            url에서 새로고침을 하면 이전 요청한 데이터가 넘어가게 된다 POST의 경우 이전의 요청한 POST가 넘어가므로
            중복요청이 된다 따라서 300번대의 규칙을 이용하여 웹 브라우저의 요청 주소를 바꿔치기 하면
            요청 URL이 변경되므로 새로고침 해도 바뀐 url이 전송된다 (PRG패턴이다)
         */
    }
/*    public String save(@RequestParam String itemName,@RequestParam int price,@RequestParam int quantity,Model model){
        Item item = new Item(itemName,price,quantity);
        itemRepository.save(item);
        model.addAttribute("item",item);
        return "/basic/item";
    }*/

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}"; // 리다이렉트 한다(300번대이고 location 부분 데이터 추가됨)
    }


    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }
}

package hello.springmvc;

import hello.springmvc.domain.item.Item;
import hello.springmvc.domain.item.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        Item item = new Item("ItemA",10000,10);
        Item saveItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(saveItem.getId());

        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll(){
        Item item1 = new Item("ItemA",10000,10);
        Item item2 = new Item("ItemB",20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> result = itemRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2); // 포함여부확인
    }

    @Test
    void updateItem(){
        Item item = new Item("ItemA",10000,10);
        Item savedItem = itemRepository.save(item);

        Long itemId = savedItem.getId();
        Item updateParam = new Item("itemB",20000,30);
        itemRepository.update(itemId,updateParam);

        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
    }
}

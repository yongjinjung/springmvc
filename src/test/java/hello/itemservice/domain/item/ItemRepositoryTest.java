package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item save = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(save.getId());
        Assertions.assertThat(findItem).isEqualTo(save);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 10000, 10);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        Assertions.assertThat(items.size()).isEqualTo(2);
    }

    @Test
    void update() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item itemSave = itemRepository.save(item1);
        Long itemId = itemSave.getId();

        //when
        Item item = new Item("item2", 20000, 30);
        itemRepository.update(itemId, item);

        //then
        Item findItem = itemRepository.findById(itemId);
        Assertions.assertThat(findItem.getItemName()).isEqualTo(item.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(item.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(item.getQuantity());
    }
}
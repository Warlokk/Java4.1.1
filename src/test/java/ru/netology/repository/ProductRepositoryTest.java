package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    Product first = new Book(1, "Мизери", 150, "С.Кинг");
    Product second = new Book(2, "tестирование dot com", 200, "Р.Савин");
    Product third = new Book(3, "Противостояние", 150, "С.Кинг");
    Product fourth = new Smartphone(4, "Мизери", 15000, "Xiaomi");
    Product fifth = new Smartphone(5, "Iphone", 80000, "С.Кинг");

    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
    }

    @Test
    void shouldRemoveById() {
        setUp();
        Product[] expected = new Product[]{first, second, fourth, fifth};
        repository.removeById(3);
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldExceptionRemoveNotExistedId() {
        setUp();
        assertThrows(NotFoundException.class, () -> repository.removeById(6));
    }
}
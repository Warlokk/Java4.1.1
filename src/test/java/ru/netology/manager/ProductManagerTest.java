package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductManager manager;
    Product first = new Book(1, "Мизери", 150, "С.Кинг");
    Product second = new Book(2, "tестирование dot com", 200, "Р.Савин");
    Product third = new Book(3, "Противостояние", 150, "С.Кинг");
    Product fourth = new Smartphone(4, "Мизери", 15000, "Xiaomi");
    Product fifth = new Smartphone(5, "Iphone", 80000, "С.Кинг");

    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
    }

    @Test
    void searchByName() {
        setUp();
        Product[] returned = new Product[]{first, second, third, fourth, fifth};
        doReturn(returned).when(repository).findAll();

        String search = "Мизери";
        Product[] expected = new Product[]{first, fourth};
        Product[] actual = manager.searchBy(search);
        assertArrayEquals(expected, actual);

        verify(repository).findAll();
    }

    @Test
    void searchByAuthor() {
        setUp();
        Product[] returned = new Product[]{first, second, third, fourth, fifth};
        doReturn(returned).when(repository).findAll();

        String search = "С.Кинг";
        Product[] expected = new Product[]{first, third, fifth};
        Product[] actual = manager.searchBy(search);
        assertArrayEquals(expected, actual);

        verify(repository).findAll();
    }
}
package ru.netology.product;

import org.junit.jupiter.api.Test;
import ru.netology.manager.ProductManager;
import ru.netology.product.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book = new Book(1, "Book16", 1500, "Александр Пушков");
    Product smartphone = new Smartphone(2, "Phone36", 50_000, "Nokia");
    Product product = new Smartphone(3, "Product27", 80_000, "Samsung");

    @Test
    void shouldAdd() {
        manager.add(book);
        Product[] expected = {book};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddAll() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        Product[] expected = {book, smartphone, product};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBy() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Book16";
        Product[] expected = {book};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearch() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Phone36";
        Product[] expected = {smartphone};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearch2() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Samsung";
        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

}
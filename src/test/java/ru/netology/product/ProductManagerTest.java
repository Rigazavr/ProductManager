package ru.netology.product;

import org.junit.jupiter.api.Test;
import ru.netology.manager.ProductManager;
import ru.netology.product.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {

    private final ProductRepository repo = new ProductRepository();
    private final ProductManager manager = new ProductManager(repo);
    private final Product book1 = new Book(1, "В Лесу", 120, "Олег Губкин");
    private final Product book2 = new Book(2, "Рассвет", 150, "Виктор Шар");
    private final Product book3 = new Book(3, "Рассвет", 180, "Анна Даль");
    private final Product smartphone1 = new Smartphone(4, "Galaxy S21", 8500, "Samsung");
    private final Product smartphone2 = new Smartphone(5, "Redmi 8T", 3500, "Xiaomi");


    @Test
    public void addBook() {
        manager.add(book1);

        Product[] expected = {book1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void addSmartphone() {
        manager.add(smartphone1);

        Product[] expected = {smartphone1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void addBookAndSmartphone() {
        manager.add(book1);
        manager.add(smartphone1);

        Product[] expected = {book1, smartphone1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void noMatchTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        String name = "Не книга";

        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneMatchTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        String name = "В Лесу";

        Product[] expected = {book1};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void nameMatchTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);

        String name = "Рассвет";

        Product[] expected = {book2, book3};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }
}
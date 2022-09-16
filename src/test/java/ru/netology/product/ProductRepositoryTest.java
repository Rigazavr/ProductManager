package ru.netology.product;

import org.junit.jupiter.api.Test;
import ru.netology.product.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    Product book = new Book(1, "Book16", 1500, "Александр Пушков");
    Product smartphone = new Smartphone(2, "Phone36", 50_000, "Nokia");
    Product product = new Smartphone(3, "Product27", 80_000, "Samsung");
    @Test
    public void shouldSaveOneItem() {
        repository.save(book);
        Product[] expected = {book};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAll() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        Product[] expected = { book, smartphone, product};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);

    }
    @Test
    public void shouldRemoveById() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        repository.removeById(2);
        Product[] expected = { book, product };
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveAllById() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        repository.removeById(1);
        repository.removeById(2);
        repository.removeById(3);
        Product[] expected = {};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}
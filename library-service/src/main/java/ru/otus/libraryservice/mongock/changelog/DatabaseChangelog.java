package ru.otus.libraryservice.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.libraryservice.entity.Author;
import ru.otus.libraryservice.entity.Book;
import ru.otus.libraryservice.entity.Genre;
import ru.otus.libraryservice.repository.AuthorRepository;
import ru.otus.libraryservice.repository.BookRepository;
import ru.otus.libraryservice.repository.GenreRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "PavlovDV", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "add-authors", author = "PavlovDV")
    public void addAuthor(AuthorRepository authorRepository) {
        authorRepository.deleteAll();
        authorRepository.save(new Author("1", "Джеймс Клавелл"));
        authorRepository.save(new Author("2", "Эрих Мария Ремарк"));
        authorRepository.save(new Author("3", "Джон Ро́нальд Ру́эл То́лкин"));
        authorRepository.save(new Author("4", "Чарльз Диккенс"));
        authorRepository.save(new Author("5", "Джон Стейнбек"));
        authorRepository.save(new Author("6", "Роберт Стивенсон"));
        authorRepository.save(new Author("7", "Александр Дюма"));
        authorRepository.save(new Author("8", "Стивен Кинг"));
        authorRepository.save(new Author("9", "Уилбур Смит"));
    }

    @ChangeSet(order = "003", id = "add-genres", author = "PavlovDV")
    public void addGenre(GenreRepository genreRepository) {
        genreRepository.deleteAll();
        genreRepository.save(new Genre("1", "Приключения"));
        genreRepository.save(new Genre("2", "Роман"));
        genreRepository.save(new Genre("3", "Классическая литература"));
        genreRepository.save(new Genre("4", "Зарубежная литература"));
        genreRepository.save(new Genre("5", "Детские книги"));
        genreRepository.save(new Genre("6", "Детективы"));
        genreRepository.save(new Genre("7", "Фэнтези"));
        genreRepository.save(new Genre("8", "Фантастика"));
        genreRepository.save(new Genre("9", "Современная проза"));
    }

    @ChangeSet(order = "004", id = "add-books", author = "PavlovDV")
    public void addBook(BookRepository bookRepository) {
        bookRepository.deleteAll();
        bookRepository.save(new Book("1", "Сегун",
                                      new Genre("1",
                                              "Приключения"),
                                      new Author("1", "Джеймс Клавелл"),
                "Роман «Сегун» основан на реальных событиях. Прототипом главного героя послужил английский штурман Уильям Адамс, который считается первым британцем, побывавшем в Японии. В 1598 году он в звании штурмана отправился в восточную экспедицию, целью которой было достичь берегов Южной Америки и продать там европейские товары (изделия текстильной промышленности, оружие, порох). Путешествие проходило в очень трудных условиях, многие моряки погибли во время плавания. У южноамериканского побережья несколько кораблей разметал шторм, другие попали в плен к португальцам и испанцам, так что далее на восток отправился только один корабль, на котором находился Адамс. - Читайте подробнее на FB.ru: https://fb.ru/article/264079/roman-segun-soderjanie-i-otzyivyi",
                "https://www.ozon.ru/person/klavell-dzheyms-257909/"));

        bookRepository.save(new Book("2", "Триумфальная арка", new Genre("2", "Роман"), new Author("1", "Эрих Мария Ремарк"),
                "Роман известного немецкого писателя Э. М. Ремарка (1898 - 1970) \"Триумфальная арка\" повествует о жизни в Европе накануне Второй мировой войны и посвящен хорошо знакомой автору теме эмиграции.\n",
                "https://www.ozon.ru/product/triumfalnaya-arka-remark-erih-mariya-250444238/?sh=fwezeQbC"));
    }
}

package ziye.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ziye.springframework.spring5webapp.model.Author;
import ziye.springframework.spring5webapp.model.Book;
import ziye.springframework.spring5webapp.model.Publisher;
import ziye.springframework.spring5webapp.repositories.AuthorRepository;
import ziye.springframework.spring5webapp.repositories.BookRepository;
import ziye.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initData();
    }

    private void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("pub");
        publisher.setAddress("add1");

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("aaa","1234",publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(publisher);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod","Johnson");
        Book eee = new Book("eee","1222",publisher);
        rod.getBooks().add(eee);

        authorRepository.save(rod);
        bookRepository.save(eee);
    }

}

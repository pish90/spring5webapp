package com.peter.spring5webapp.bootstrap;

import com.peter.spring5webapp.model.Author;
import com.peter.spring5webapp.model.Book;
import com.peter.spring5webapp.model.Publisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.peter.spring5webapp.repositories.AuthorRepository;
import com.peter.spring5webapp.repositories.BookRepository;
import com.peter.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
        private PublisherRepository publisherRepository;
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
                this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();		
	}
	
	private void initData() {
            
            Publisher pub1 = new Publisher();
            pub1.setName("HarperCollins");
            publisherRepository.save(pub1);
            
            //Eric
            Author eric = new Author("Eric", "Evans");
            Book ddd = new Book("Domain Driven Design", "1234", pub1);
            eric.getBooks().add(ddd);
            ddd.getAuthors().add(eric);

            authorRepository.save(eric);
            bookRepository.save(ddd);

            Publisher pub2 = new Publisher();
            pub2.setName("Worx");
            publisherRepository.save(pub2);
            
            //Rod     
            Author rod = new Author("Rod", "Johnson");
            Book noEJB = new Book("J2EE Development without EJB", "23444", pub2);
            rod.getBooks().add(noEJB);

            authorRepository.save(rod);
            bookRepository.save(noEJB);
	}
}

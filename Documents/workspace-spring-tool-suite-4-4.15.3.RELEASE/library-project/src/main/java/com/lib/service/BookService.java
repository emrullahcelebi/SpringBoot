package com.lib.service;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lib.controller.dto.AddBookRequestDTO;
import com.lib.controller.dto.ListBooksForUserDTO;
import com.lib.domain.Book;
import com.lib.exception.ResourceNotFoundException;
import com.lib.repository.BookRepository;
import com.lib.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor//bunu ekleyince gerek kalmiyor Autowired
@NoArgsConstructor
@Service
public class BookService {


	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	public static Book book;
	
	@Autowired
	public static ListBooksForUserDTO bookRequestDTO;

	//LoginRequest loginRequest;
	@Autowired
	UserService userService;
	
	
	
	public void addBook(@Valid AddBookRequestDTO request) {
		
		Book book = new Book();
		        book.setBookName(request.getBookName());
		        book.setAuthorName(request.getAuthorName());
		        book.setCategory(request.getCategory());
		        book.setStatus(request.getStatus());
		        book.setDate(request.getDate());
		        book.setOwner(request.getOwner());
		        bookRepository.save(book);
		
	}
	public List<Book> getAllBooks() {
		
		return bookRepository.findAll();
	}
	
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
		
	}
	
	
	//------------------------------------------------------------------------
	
	
	public List<ListBooksForUserDTO> getBookForUser() {
        return bookRepository.findAllBook();
	}
	//----------------------------------------------------------------
	

        
        	
           
       
	
	
	
	
	public void updateBook(Long id, AddBookRequestDTO request) {
        boolean exist = bookRepository.existsById(id);
        	
        Book book = findBook(id);
        	if (exist) {
            book.setBookName(request.getBookName());
            book.setAuthorName(request.getAuthorName());
            book.setCategory(request.getCategory());
            book.setStatus(request.getStatus());
            book.setDate(request.getDate());
            book.setOwner(request.getOwner());
            bookRepository.save(book);
        }
        else {
            throw new ResourceNotFoundException("Could not found id: "+id);
        }
	   
	}
	
	private Book findBook(Long id ) {
		return bookRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Book not found with id : "+id));
		
	}
	
	
	
	//alinacak kitap baskasinda yoksa alinabilir
	//TODO hata kodunu yonetmek lazim
	//kullanici 3 den fazla kitap alamaz
	public void getBook(Long id,String mail) {
			
		int ownerBooks = 0;
			
		for (Book  w: bookRepository.findAllByOwner(mail)) {
			ownerBooks++;
		}
		
		Book book = findBook(id);
		if (book.getStatus()==true && ownerBooks<3) {
			book.setOwner(mail);
			book.setStatus(false);
			book.setDate(LocalDate.now().toString());
			bookRepository.save(book);//+1-+2-+3
		}else {
			System.out.println(id+" li kitap baskasi tarafindan alinmistir");
			//throw new ResourceNotFoundException("Could not found available book with that id : "+id);
		}		
	}
	
	
	
	
//public void getBook(Long id,String loginOlanUserMaili) {
//		
//		//String loginMail = loginRequest.getUserMail();
//		//System.out.println(loginMail);
//		User loginUser=  (userService.findUserByMailUser(UserJWTController.loginOlanUserMaili));
//		
//		Book book= findBook(id);
//			
//			book.setOwner(loginUser.getUserMail());
//			book.setStatus(false);
//			book.setDate(LocalDate.now().toString());
//			bookRepository.save(book);										
//	}
	
//  kulanici kitap iade methodu  
    public void returnBook(Long id, String mail) {

        Book book = findBook(id);
        book.setOwner("");
        book.setStatus(true);
        book.setDate("");
        bookRepository.save(book);

    }
	public List<Book> getMyBooks(String mail) {
		List<Book> myBooks= bookRepository.findAllByOwner(mail);
		
		return myBooks;
	}
	
//	public List<Book> getAvailableBooks(Boolean isAvaible) {
//
//        return bookRepository.getAvailableBooks(isAvaible);
//    }
	
	
	public List<Book> getAvailableBooks(Boolean status) {

        return bookRepository.bringAvailableBooks(status);
    }
	
	
}
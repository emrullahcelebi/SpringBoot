package com.lib.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lib.security.JwtUtils;
import com.lib.service.BookService;
import com.lib.service.UserService;
import com.lib.controller.dto.AddBookRequestDTO;
import com.lib.controller.dto.LoginRequest;
import com.lib.controller.dto.RegisterRequest;
import com.lib.controller.dto.UpdateRequestDTO;
import com.lib.domain.Book;
import com.lib.domain.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
@NoArgsConstructor
public class UserJWTController {
	
	
	@Autowired
	public static String loginOlanUserMaili;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String,String>> registerUser (@Valid @RequestBody RegisterRequest request ) {
		userService.registerUser(request);		
		Map<String,String> map = new HashMap<>();
		map.put("message",	" User registered successfuly");
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.CREATED);		
	}
	
	@PostMapping("/login")//burayi post yaptik!!!!!
	public ResponseEntity<Map<String,String>> login(@Valid @RequestBody com.lib.controller.dto.LoginRequest request){
		
		Authentication  authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUserMail(), request.getPassword()));
		
		//loginOlanUserMaili=request.getUserMail();
		String token = jwtUtils.generateToken(authentication);
		
		Map<String,String> map = new HashMap<>();
		map.put("token",	token);
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.ACCEPTED);		
	}
	
	@GetMapping("/listUsers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> getAll() {		
		List<User> users=  userService.getAll();
		return ResponseEntity.ok(users);		
	}
	
	
	//***************User silme***********************
	@DeleteMapping("/deleteUser/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")//sadece admin silebilsin.
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        Map<String,String> map = new HashMap<>();
        map.put("message", "User is deleted successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map,HttpStatus.OK);
        //todo kullanıcının iade etmediği kitabı varsa silinemesin.      
    }
	
	@PutMapping("/updateUser/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
	public ResponseEntity<Map<String, String>> updateUser( @PathVariable Long id, @RequestBody UpdateRequestDTO updateRequestDTO){
		
		userService.updateUser(id,updateRequestDTO);
		Map<String,String> map = new HashMap<>();
		map.put("message", "User is updated successfuly");
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	//******************kitap ekleme***************************
	@PostMapping("/addBook")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Map<String,String>> addBook (@Valid @RequestBody AddBookRequestDTO request ) {
		bookService.addBook(request);		
		Map<String,String> map = new HashMap<>();
		map.put("message",	" Book added successfuly");
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.CREATED);		
	}
	
	@GetMapping("/listBooks")
	@PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
	public ResponseEntity<List<Book>> getAllBooks() {		
		List<Book> books=  bookService.getAllBooks();
		return ResponseEntity.ok(books);		
	}
	
	
	//*************Book silme********************
	@DeleteMapping("/deleteBook/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")//sadece admin silebilsin.
    public ResponseEntity<Map<String,String>> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        Map<String,String> map = new HashMap<>();
        map.put("message", "Book is deleted successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map,HttpStatus.OK);
           
    }
	
	//****************Book guncelleme*******************
	
	@PutMapping("/updateBook/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Map<String, String>> updateBook( @PathVariable Long id, @RequestBody AddBookRequestDTO addBookRequestDTO){
		
		bookService.updateBook(id,addBookRequestDTO);
		Map<String,String> map = new HashMap<>();
		map.put("message", "Book is updated successfuly");
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	
	
	//Kullanuci kitap alacak
	
	@PutMapping("/getBook/{id}")//put yaptik!!!!
	@PreAuthorize("hasRole('ROLE_USER')")
	  public ResponseEntity<Map<String,String>> getBook(@PathVariable("id") Long id,HttpServletRequest request) {
	
		String mail=(String) request.getAttribute("mail");
		//System.out.println(request.getAttribute("password"));
		bookService.getBook(id,mail);
        Map<String,String> map = new HashMap<>();
        map.put("message", "Book is taken successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	
//	@PutMapping("/getBook/{id}")//kitap bilgilerini güncellediğimiz için put olsun.
//	@PreAuthorize("hasRole('ROLE_USER')")
//	 public ResponseEntity<Map<String,String>> getBook(@PathVariable("id") Long id,String loginOlanUserMaili ) {
//	
//        bookService.getBook(id,UserJWTController.loginOlanUserMaili);
//        Map<String,String> map = new HashMap<>();
//        map.put("message", "Book is taken successfuly");
//        map.put("status", "true");
//        map.put("taken by", UserJWTController.loginOlanUserMaili);
//        return new ResponseEntity<>(map,HttpStatus.OK);		
//	}
//	
	
	//**  Kullanici Kitap Iade Edecek   *

    @PutMapping("/returnBook/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String,String>> returnBook(@PathVariable("id") Long id,HttpServletRequest request) {

        String mail = (String) request.getAttribute("mail");
        bookService.returnBook(id,mail);
        Map<String,String> map = new HashMap<>();
        map.put("message", "Book is returned successfuly");
        map.put("status", "true");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    
    
//  Kullanici aldigi kitaplari Listelesin  **
    
    @GetMapping("/listMyBooks")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<Book>> getMyBooks(HttpServletRequest request) {
    	
    	String mail = (String) request.getAttribute("mail");
      List<Book> books=  bookService.getMyBooks(mail);

      return ResponseEntity.ok(books);
    }
    
    
    
    @GetMapping("/availableBook")
    @PreAuthorize("hasRole('ADMIN')")
 public ResponseEntity<List<Book>> availableBook( Boolean isAvailable) {
        isAvailable=true;

        List<Book> books=  bookService.getAvailableBooks(isAvailable);
        return ResponseEntity.ok(books);
}

    
//    @GetMapping("/status/{isAvailable}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//     public ResponseEntity<List<Book>> availableBooks(@PathVariable("isAvailable")Boolean status) {
//      List<Book> books=  bookService.getAvailableBooks(status);
//      return ResponseEntity.ok(books);
//  }
    
   
	
	
}
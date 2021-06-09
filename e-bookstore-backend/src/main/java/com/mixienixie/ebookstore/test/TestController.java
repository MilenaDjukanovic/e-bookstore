package com.mixienixie.ebookstore.test;

import com.mixienixie.ebookstore.core.requests.CreateAuthorRequest;
import com.mixienixie.ebookstore.core.requests.CreateBookRequest;
import com.mixienixie.ebookstore.core.requests.CreateCategoryRequest;
import com.mixienixie.ebookstore.core.requests.CreatePublishingHouseRequest;
import com.mixienixie.ebookstore.repo.authority.RoleRepository;
import com.mixienixie.ebookstore.repo.authority.UserRepository;
import com.mixienixie.ebookstore.repo.authority.entity.RoleEntity;
import com.mixienixie.ebookstore.repo.core.entity.AuthorDto;
import com.mixienixie.ebookstore.repo.core.entity.AuthorEntity;
import com.mixienixie.ebookstore.repo.core.entity.BookDto;
import com.mixienixie.ebookstore.repo.core.entity.BookEntity;
import com.mixienixie.ebookstore.repo.core.entity.CategoryDto;
import com.mixienixie.ebookstore.repo.core.entity.PublishingHouseDto;
import com.mixienixie.ebookstore.service.AuthorService;
import com.mixienixie.ebookstore.service.BookService;
import com.mixienixie.ebookstore.service.CategoryService;
import com.mixienixie.ebookstore.service.NotificationService;
import com.mixienixie.ebookstore.service.PublishingHouseService;
import com.mixienixie.ebookstore.service.RoleService;
import com.mixienixie.ebookstore.service.UserService;
import com.mixienixie.ebookstore.service.core.BookViewMapper;
import com.mixienixie.ebookstore.service.core.PublishingHouseViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ndjordjieski
 */
@RestController @RequestMapping("/test")
public class TestController{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublishingHouseService publishingHouseService;

    @Autowired
    private PublishingHouseViewMapper publishingHouseViewMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookViewMapper bookViewMapper;

    @GetMapping("user")
    public PublishingHouseDto user(@RequestBody @Valid CreatePublishingHouseRequest createPublishingHouseRequest){
        PublishingHouseDto x = this.publishingHouseService.create(createPublishingHouseRequest);

        System.out.println(this.roleService.getRepresentativeRoleForPublishingHouse(this.publishingHouseViewMapper.toEntity(x)));

        return x;
    }

    @GetMapping("admin")
    public String admin(){
        this.createBooks();
        this.addRandomAuthors();

        return "ADMIN";
    }

    private void sendNotifications(){
        Map<BookEntity, Integer> bookPurchase = new HashMap<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<BookDto> books = this.bookService.findAll(pageable);
        books.get().forEach(bookDto -> {
            bookPurchase.put(this.bookViewMapper.toEntity(bookDto), 5);
        });

        this.notificationService.sendBookPurchaseNotification(bookPurchase, "123123");
    }

    private void addRandomAuthors(){
        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest();
        for(int i = 0; i < 15; ++i){
            createAuthorRequest.setFirstName("Pera" + 19 + i);
            createAuthorRequest.setLastName("Zdera " + 19 + i);
            createAuthorRequest.setAbout("Pera Zdera Kralj!" + 19 + i);

            this.authorService.create(createAuthorRequest);
        }
    }

    private List<AuthorDto> getAllAuthors(){
        return null;
    }

    private void createBooks(){
        CreateAuthorRequest createAuthorRequest = new CreateAuthorRequest();
        createAuthorRequest.setFirstName("Pera");
        createAuthorRequest.setLastName("Zdera");
        createAuthorRequest.setAbout("Pera Zdera Kralj!");

        AuthorDto authorEntity = this.authorService.create(createAuthorRequest);

        createAuthorRequest = new CreateAuthorRequest();
        createAuthorRequest.setFirstName("Mika");
        createAuthorRequest.setLastName("Pika");
        createAuthorRequest.setAbout("Mika Pika Car!");

        AuthorDto authorDto2 = this.authorService.create(createAuthorRequest);

        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setName("Fiktivno");
        CategoryDto categoryDto = this.categoryService.create(createCategoryRequest);

        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setAuthorId(authorEntity.getId());
        createBookRequest.setCategoryId(categoryDto.getId());
        createBookRequest.setDescription("123123");
        createBookRequest.setPrice(10);
        createBookRequest.setTitle("Book 1");
        createBookRequest.setImage("http/FakeUrl");
        createBookRequest.setInStock(33);
        createBookRequest.setPublishingHouseId(2L);

        this.bookService.create(createBookRequest);

        createBookRequest = new CreateBookRequest();
        createBookRequest.setAuthorId(authorDto2.getId());
        createBookRequest.setCategoryId(categoryDto.getId());
        createBookRequest.setDescription("8888");
        createBookRequest.setPrice(19);
        createBookRequest.setTitle("Book 2");
        createBookRequest.setInStock(30);
        createBookRequest.setImage("123");
        createBookRequest.setPublishingHouseId(2L);

        this.bookService.create(createBookRequest);

        createBookRequest = new CreateBookRequest();
        createBookRequest.setAuthorId(authorDto2.getId());
        createBookRequest.setCategoryId(categoryDto.getId());
        createBookRequest.setDescription("123123");
        createBookRequest.setImage("123");
        createBookRequest.setTitle("Book 3");
        createBookRequest.setPrice(22);
        createBookRequest.setInStock(33);
        createBookRequest.setPublishingHouseId(3L);

        this.bookService.create(createBookRequest);

        createBookRequest = new CreateBookRequest();
        createBookRequest.setAuthorId(authorEntity.getId());
        createBookRequest.setCategoryId(categoryDto.getId());
        createBookRequest.setDescription("8888");
        createBookRequest.setTitle("Book 4");
        createBookRequest.setImage("123");
        createBookRequest.setPrice(40);
        createBookRequest.setInStock(12);
        createBookRequest.setPublishingHouseId(4L);

        this.bookService.create(createBookRequest);
    }

    @GetMapping("init")
    public RoleEntity init() throws JSONException{
        this.createBooks();
        return null;
    }
}

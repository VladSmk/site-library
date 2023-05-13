package ua.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.project1.dao.AuthorDAO;
import ua.project1.dao.BookDAO;
import ua.project1.model.Book;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    @Autowired
    public BookController(BookDAO bookDAO, AuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }


    @GetMapping()
    public String index (Model model) {
        model.addAttribute("book_list", bookDAO.getBookList());
        return "book/index";
    }

    @GetMapping("/new")
    public String create_new_book (Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("author_list", authorDAO.getAuthorList());
        return "book/new";
    }
    @PostMapping("/new")
    public String create_new_book (@ModelAttribute("book") Book book) {
        bookDAO.addBook(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit_book (@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        model.addAttribute("author_list", authorDAO.getAuthorList());
        return "book/edit";
    }
    @PatchMapping("/{id}/edit")
    public String edit_book (@ModelAttribute("book") Book book,
                               @PathVariable("id") int id) {
        book.setId(id);
        bookDAO.editBook(book);
        return "redirect:/book/"+book.getId();
    }

    @GetMapping("/{id}")
    public String look_book (@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        model.addAttribute("isBookBusy", bookDAO.isBookBusy(id));
//        System.out.println(bookDAO.isBookBusy(id));
        return "book/review";
    }

    @DeleteMapping("/{id}/delete")
    public String delete_book (@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/book";
    }
}

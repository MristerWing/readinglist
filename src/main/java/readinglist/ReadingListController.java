package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ReadingListController {

    private static ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String readerBooks(Model model, Reader reader) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
        }
        return "readingList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(Book book, Reader reader) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";
    }
}

package xyz.domza.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import xyz.domza.dto.GuestBookCommentDTO;
import xyz.domza.model.DiaryArticleModel;
import xyz.domza.model.GuestBookCommentModel;
import xyz.domza.service.DiaryService;
import xyz.domza.service.GuestBookService;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/domza")
public class DomzasWebspaceController {
    private static final Logger logger = LogManager.getLogger(DomzasWebspaceController.class);
    @Autowired
    private GuestBookService guestBookService;

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/guestBookComments")
    public List<GuestBookCommentDTO> guestBookComments() {
        return guestBookService.getComments();
    }

    @GetMapping("/admin/guestBookCommentsAdmin")
    public List<GuestBookCommentModel> guestBookCommentsAdmin() {
        return guestBookService.getCommentsAdmin();
    }

    @DeleteMapping("/admin/guestBookCommentsAdmin/{id}")
    public void deleteGuestBookComment(@PathVariable int id) {
        guestBookService.delete(id);
    }

    @PostMapping("/submitGuestBookComment")
    public ResponseEntity<Void> submitGuestBookComment(@Valid @RequestBody GuestBookCommentDTO guestBookCommentDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        };
        guestBookService.save(new GuestBookCommentModel(
                guestBookCommentDTO.username(),
                guestBookCommentDTO.message(),
                new Date()
        ));
        logger.info("New guestbook message from: " + guestBookCommentDTO.username());
        return ResponseEntity.created(URI.create("/submitGuestBookComment")).build();
    }

    @GetMapping("/diaryArticles")
    public List<DiaryArticleModel> getDiaryArticles() {
        return new ArrayList<>(diaryService.getArticles());
    }

    /**
     * @param diaryArticleModel - If contains id and that id exists it will update that article,
     *                          else it will just create a new article and assign ID automatically
     *                          while ignoring id from the object passed.
     */
    @PostMapping("/admin/updateDiaryArticle")
    public void updateDiaryArticle(@RequestBody DiaryArticleModel diaryArticleModel) {
        diaryService.save(diaryArticleModel);
    }

    @DeleteMapping("/admin/deleteArticle/{id}")
    public void deleteArticle(@PathVariable int id) {
        diaryService.delete(id);
    }
}

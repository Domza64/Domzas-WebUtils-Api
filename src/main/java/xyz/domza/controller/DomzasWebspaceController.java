package xyz.domza.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.domza.dto.GuestBookCommentDTO;
import xyz.domza.model.DiaryArticleModel;
import xyz.domza.model.GuestBookCommentModel;
import xyz.domza.service.DiaryService;
import xyz.domza.service.GuestBookService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/domza")
public class DomzasWebspaceController {
    @Autowired
    private GuestBookService guestBookService;

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/guestBookComments")
    public List<GuestBookCommentDTO> getComments() {
        return guestBookService.getComments();
    }

    @PostMapping("/submitGuestBookComment")
    public void submitGuestBookComment(@Valid @RequestBody GuestBookCommentDTO guestBookCommentDTO) {
        guestBookService.save(new GuestBookCommentModel(
                guestBookCommentDTO.username(),
                guestBookCommentDTO.message(),
                new Date()
        ));
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
    @PostMapping("/updateDiaryArticle")
    public void updateDiaryArticle(@RequestBody DiaryArticleModel diaryArticleModel) {
        diaryService.save(diaryArticleModel);
    }

    @DeleteMapping("/deleteArticle/{id}")
    public void deleteArticle(@PathVariable int id) {
        diaryService.delete(id);
    }
}

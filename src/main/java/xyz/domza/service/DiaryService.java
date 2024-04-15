package xyz.domza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import xyz.domza.dto.GuestBookCommentDTO;
import xyz.domza.model.DiaryArticleModel;
import xyz.domza.model.GuestBookCommentModel;
import xyz.domza.repository.DiaryRepository;
import xyz.domza.repository.GuestBookRepository;

import java.util.*;

@Service
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    public List<DiaryArticleModel> getArticles() {
        // TODO - Load articles 10 by 10
        Iterable<DiaryArticleModel> iterable = diaryRepository.findAll();
        Deque<DiaryArticleModel> deque = new ArrayDeque<>();

        iterable.forEach(deque::addFirst);

        return List.copyOf(deque);
    }

    public void save(DiaryArticleModel diaryArticleModel) {
        diaryRepository.save(diaryArticleModel);
    }

    public void delete(String id) {
        diaryRepository.deleteById(id);
    }
}

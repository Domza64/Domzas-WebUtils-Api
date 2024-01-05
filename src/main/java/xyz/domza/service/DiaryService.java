package xyz.domza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import xyz.domza.dto.GuestBookCommentDTO;
import xyz.domza.model.DiaryArticleModel;
import xyz.domza.model.GuestBookCommentModel;
import xyz.domza.repository.DiaryRepository;
import xyz.domza.repository.GuestBookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {
    @Autowired
    private DiaryRepository diaryRepository;

    public List<DiaryArticleModel> getArticles() {
        return new ArrayList<>(diaryRepository.findAllBy());
    }

    public void save(DiaryArticleModel diaryArticleModel) {
        diaryRepository.save(diaryArticleModel);
    }

    public void delete(int id) {
        diaryRepository.deleteById(id);
    }
}

package xyz.domza.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.domza.model.DiaryArticleModel;

import java.util.List;

public interface DiaryRepository extends CrudRepository<DiaryArticleModel, Integer> {
    List<DiaryArticleModel> findAllByOrderByCreatedAtDesc();
}
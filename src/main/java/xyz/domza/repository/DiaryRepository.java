package xyz.domza.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.domza.model.DiaryArticleModel;

import java.util.List;

public interface DiaryRepository extends MongoRepository<DiaryArticleModel, String> {

}
package xyz.domza.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.domza.dto.GuestBookCommentDTO;
import xyz.domza.model.GuestBookCommentModel;
import java.util.List;

public interface GuestBookRepository extends MongoRepository<GuestBookCommentModel, String> {
    List<GuestBookCommentDTO> findAllBy();
}

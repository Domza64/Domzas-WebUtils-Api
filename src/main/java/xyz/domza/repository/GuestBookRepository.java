package xyz.domza.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.domza.dto.GuestBookCommentDTO;
import xyz.domza.model.GuestBookCommentModel;
import java.util.List;

public interface GuestBookRepository extends CrudRepository<GuestBookCommentModel, Integer> {
    List<GuestBookCommentDTO> findAllBy();
}

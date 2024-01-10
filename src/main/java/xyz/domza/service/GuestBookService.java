package xyz.domza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.domza.dto.GuestBookCommentDTO;
import xyz.domza.model.GuestBookCommentModel;
import xyz.domza.repository.GuestBookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GuestBookService {
    @Autowired
    private GuestBookRepository guestBookRepository;

    public List<GuestBookCommentDTO> getComments() {
        return new ArrayList<>(guestBookRepository.findAllBy());
    }

    public List<GuestBookCommentModel> getCommentsAdmin() {
        Iterable<GuestBookCommentModel> iterable = guestBookRepository.findAll();
        List<GuestBookCommentModel> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    public Optional<GuestBookCommentModel> findById(Integer id) {
        return guestBookRepository.findById(id);
    }

    public void save(GuestBookCommentModel userCommentModel) {
        guestBookRepository.save(userCommentModel);
    }

    public void delete(int id) {
        guestBookRepository.deleteById(id);
    }
}

package ex.smart.library.service;

import ex.smart.library.dto.UserBookDto;
import ex.smart.library.model.Book;
import ex.smart.library.model.User;
import ex.smart.library.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapService {

    @Autowired
    UserRepo userRepo;

    public List<UserBookDto> getAllUsersLocation(){
        return ((List<User>) userRepo
                .findAll())
                .stream()
                .map(this::convertToUserBookDto)
                .collect(Collectors.toList());
    }

    private UserBookDto convertToUserBookDto(User user){
        UserBookDto userBookDto = new UserBookDto();
        userBookDto.setUserId(user.getId());
        userBookDto.setUsername(user.getUsername());
        Book book = user.getBook();
        userBookDto.setBookName(book.getBookName());
        userBookDto.setCategory(book.getCategory());

        return userBookDto;
    }
}

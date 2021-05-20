package ex.smart.library.controller;

import ex.smart.library.dto.UserBookDto;
import ex.smart.library.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/map")
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping
    @ResponseBody
    public List<UserBookDto> findCollectionDto(){
        List<UserBookDto> userBookDto =  mapService.getAllUsersLocation();
        return userBookDto;
    }
}

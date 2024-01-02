package com.example.srms.Controller;


import com.example.srms.Model.Club;
import com.example.srms.Service.ClubService;
import com.example.srms.dto.ClubDto;
import com.example.srms.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/clubs/")
public class ClubController {

    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping(path = "get-clubs")
    public ResponseEntity<?> getAllclubs (){
        Response<Club> responseList = clubService.findAllClubs();

        return  ResponseEntity.ok()
                .body(responseList);
    }

    @PostMapping(path = "new-club")

    public  ResponseEntity<?> RegisterClub(@RequestBody ClubDto clubDto){
        Response<Club> clubResponse = clubService.createClub(clubDto);

        return ResponseEntity.ok()
                .body(clubResponse);
    }
}

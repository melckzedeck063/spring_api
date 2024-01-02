package com.example.srms.Service;

import com.example.srms.Model.Club;
import com.example.srms.dto.ClubDto;
import com.example.srms.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClubService  {
    Response<Club>  findAllClubs();

    Response<Club> createClub(ClubDto clubDto);
}

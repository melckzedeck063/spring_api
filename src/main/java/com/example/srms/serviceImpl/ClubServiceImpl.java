package com.example.srms.serviceImpl;

import com.example.srms.Model.Club;
import com.example.srms.Repository.ClubRepository;
import com.example.srms.Service.ClubService;
import com.example.srms.dto.ClubDto;
import com.example.srms.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;


    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }
//
//    @Override
//    public ResponseEntity<?> findAllClubs() {
//        Response<Club> = clubRepository.findAll();
//
//        return  ResponseEntity.ok()
//                .body(clubs);
//    }

    @Override
    public Response<Club> findAllClubs() {

        try {
            List<Club> response = clubRepository.findAll();

            return new Response<>(false, 200, response, "Succesfull");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(true, 403, "Operation Failed: " + e.getMessage());
        }
    }

    @Override
    public Response<Club> createClub(ClubDto clubDto) {

        try {
            Club newClub = new Club();

            Optional<Club> optionalClub = clubRepository.findClubByTitle(clubDto.getTitle());

            if (optionalClub.isPresent()) {
                throw new IllegalStateException("Club name exists");
            }

            if (clubDto.getTitle() != null && !clubDto.getTitle().isEmpty()) {
                newClub.setTitle(clubDto.getTitle());
            }

            if (clubDto.getContent() != null && !clubDto.getContent().isEmpty()) {
                newClub.setContent(clubDto.getContent());
            }

            if
            (clubDto.getPhotUrl() != null && !clubDto.getPhotUrl().isEmpty()) {
                newClub.setPhotUrl(clubDto.getPhotUrl());
            }

            Club club = clubRepository.save(newClub);
            return new Response<>(false, 200, club, "Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(true, 403, "Operation Failed: " + e.getMessage());
        }

    }


}

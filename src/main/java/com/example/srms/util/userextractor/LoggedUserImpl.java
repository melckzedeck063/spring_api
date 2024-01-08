package com.example.srms.util.userextractor;

import com.example.srms.Model.Student;
import com.example.srms.Repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;


@Slf4j
@Service
public class LoggedUserImpl implements  LoggedUser {

    private static final Logger logger = LoggerFactory.getLogger(LoggedUserImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserInfo getInfo() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null){
            log.info("Null Auth detected");

            return null;
        }
        else {
            UserInfo userInfo =  new UserInfo();

            try{
                ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
                        .registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());

                String jsonAuth =  mapper.writeValueAsString(auth);

                HashMap<String, Object> results =  new ObjectMapper().readValue(jsonAuth, HashMap.class);

                if(results.get("principal") != null){
                    logger.info("PRINCIPAL {} " , results.get("principal"));

                    if(results.get("principal").toString().contains("anonymousUser"))
                        return  null;

                    HashMap<String, Object>  principal =  (HashMap<String, Object>)  results.get("principal");

                    logger.info("user principal found {} " , principal);

                    Object id =  principal.get("id");

                    userInfo.setId(Long.parseLong(id.toString()));

                    return userInfo;
                }
            }
            catch (Exception e){
                logger.error("-----  Error has occured on authentication " + e.getMessage() + " ------- " );
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Student getUser() {

        if(getInfo()  != null &&  getInfo().getId() != null)
            return studentRepository.findById(getInfo().getId()).orElse(null);

        return null;
    }
}

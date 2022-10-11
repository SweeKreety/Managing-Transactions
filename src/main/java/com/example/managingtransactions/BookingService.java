package com.example.managingtransactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Component allows to detect custom beans
@Component
public class BookingService {
    private final static Logger logger= LoggerFactory.getLogger(BookingService.class);

     private final JdbcTemplate jdbcTemplate;  //performs all database related interactions

    public BookingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate= jdbcTemplate;
    }

    //Transactional rollbacks data to previous state in case of failure

    @Transactional
    public void book(String... persons){
        for(String person: persons){
            logger.info(" "+ person + "in a seat....");
            jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values(?)", person);
        }
    }
    public List<String> findAllBookings(){
        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS", (rs, rowNum) -> rs.getString("FIRST_NAME"));
    }
}

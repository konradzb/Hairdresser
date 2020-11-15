package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.HairstyleDao;
import com.hairdresser.booking.exception.HairstyleNotFoundException;
import com.hairdresser.booking.model.Hairstyle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HairstyleService {
    @Autowired @Qualifier("fake")
    private final HairstyleDao hairstyleDao;

    public Hairstyle insertHairstyle(Hairstyle newHairstyle) {
        return hairstyleDao.insertHairstyle(newHairstyle);
    }

    public Hairstyle getHairstyleById(UUID id) {
        Optional<Hairstyle> optionalHairstyle = hairstyleDao.getHairstyleById(id);
        return optionalHairstyle.orElseThrow(HairstyleNotFoundException::new);
    }

    public List<Hairstyle> getAllHairstyles() {
        return hairstyleDao.getAllHairstyles();
    }

    public Hairstyle deleteHairstyleById(UUID id) {
        Optional<Hairstyle> optionalHairstyle = hairstyleDao.deleteHairstyleById(id);
        return optionalHairstyle.orElseThrow(HairstyleNotFoundException::new);
    }

    //Edit only variables which are't nulls, otherwise do nothing
    public Hairstyle editHairstyleById(UUID id, Hairstyle newHairstyle) {
        //Get hairstyle which will be edited
        Optional<Hairstyle> hairstyleToEdit = hairstyleDao.getHairstyleById(id);

        //Store new variables
        String newName = newHairstyle.getName();
        String newDescription = newHairstyle.getDescription();
        float newPrice = newHairstyle.getPrice();
        int newTime = newHairstyle.getTime();

        //Replace variables given by user
        hairstyleToEdit.ifPresent(hs-> {
            if(newName != null) hs.setName(newName);
            if(newDescription != null) hs.setDescription(newDescription);
            if(newPrice > 0) hs.setPrice(newPrice);
            if(newTime > 0) hs.setTime(newTime);
        });

        //Replace old object with the edited one in database
        Optional<Hairstyle> hairstyleEdited = hairstyleDao.editHairstyleById(id, hairstyleToEdit.get());

        return hairstyleEdited.orElseThrow(HairstyleNotFoundException::new);
    }
}

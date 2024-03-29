package com.hairdresser.booking.dao.fake;

import com.google.common.collect.Lists;
import com.hairdresser.booking.dao.HairstyleDao;
import com.hairdresser.booking.model.Hairstyle;
import com.hairdresser.booking.model.input.HairstyleInput;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeHairstyle")
public class FakeHairstyleDaoAccessService implements HairstyleDao {

    private List<Hairstyle> hairstyles = Lists.newArrayList(
            new Hairstyle(UUID.fromString("2b01e86f-f5ce-4415-9c9e-40340e201b9e").toString(), "first", "difficult hairstyle", 35, 20),
            new Hairstyle(UUID.fromString("a1fd9c09-c064-4c26-9d18-6151a369eeec").toString(), "second", "easy hairstyle", 55, 60),
            new Hairstyle(UUID.randomUUID().toString(), "third", "third desc", 150, 45),
            new Hairstyle(UUID.randomUUID().toString(), "forth", "forth desc", 180, 20),
            new Hairstyle(UUID.randomUUID().toString(), "fifth", "fifth desc", 35, 60),
            new Hairstyle(UUID.randomUUID().toString(), "sixth", "sixth desc", 70, 45),
            new Hairstyle(UUID.randomUUID().toString(), "seventh", "seventh desc", 20, 20),
            new Hairstyle(UUID.randomUUID().toString(), "eighth", "eighth desc", 50, 60),
            new Hairstyle(UUID.randomUUID().toString(), "ninth", "ninth desc", 15, 45),
            new Hairstyle(UUID.randomUUID().toString(), "tenth", "tenth desc", 40, 45)
    );

    @Override
    public Hairstyle insertHairstyle(Hairstyle hairstyle) {
        hairstyles.add(hairstyle);
        return hairstyle;
    }

    @Override
    public Optional<Hairstyle> getHairstyleById(String id) {
        return hairstyles.stream().filter(hairstyle -> hairstyle.getId().equals(id)).findFirst();
    }

    @Override
    public List<Hairstyle> getAllHairstyles() {
        return hairstyles;
    }

    @Override
    public Optional<Hairstyle> deleteHairstyleById(String id) {
        Optional<Hairstyle> hairstyleToDelete = getHairstyleById(id);
        hairstyleToDelete.ifPresent(hs-> hairstyles.remove(hs));

        return hairstyleToDelete;
    }

    @Override
    //Edit only variables which are not nulls, otherwise do nothing
    public Optional<Hairstyle> editHairstyleById(Hairstyle hairstyle) {
        Optional<Hairstyle> hairstyleToEdit = getHairstyleById(hairstyle.getId());
        hairstyleToEdit.ifPresent(hs-> hs = hairstyle);

        return hairstyleToEdit;
    }


}

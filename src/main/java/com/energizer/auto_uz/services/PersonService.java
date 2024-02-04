package com.energizer.auto_uz.services;

import com.energizer.auto_uz.dto.reques.AdvertisementRequest;
import com.energizer.auto_uz.dto.reques.AdvertisementUpdateRequest;
import com.energizer.auto_uz.dto.reques.RegisterRequest;
import com.energizer.auto_uz.dto.response.EagerAdvertisementResponse;
import com.energizer.auto_uz.dto.response.LazyAdvertisementResponse;
import com.energizer.auto_uz.exceptions.EntityNotFoundException;
import com.energizer.auto_uz.exceptions.FileNotExistException;
import com.energizer.auto_uz.models.users.Advertisement;
import com.energizer.auto_uz.models.users.AdvertisementPhoto;
import com.energizer.auto_uz.models.users.Person;
import com.energizer.auto_uz.repositories.AdvertisementPhotoRepository;
import com.energizer.auto_uz.repositories.AdvertisementRepository;
import com.energizer.auto_uz.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {
    @Transactional(readOnly = true)
    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }
    @Transactional(readOnly = true)
    public Person getUserEntity(String email) {
        return findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }
    public void register(Person person) {
        personRepository.save(person);
    }
    public void register(RegisterRequest registerRequest) {
        register(conversionService.convert(registerRequest, Person.class));
    }
    public void addAdvertisement(String email, AdvertisementRequest dto) {
        Person user = getUserEntity(email);
        user.addAdvertisement(new Advertisement(
                dto.mileage(),
                dto.description(),
                dto.price(),
                new Date(),
                markService.getGenerationEntity(dto.generation_id()),
                characteristicService.getComponentEntity(dto.corpus_id()),
                characteristicService.getComponentEntity(dto.engine_id()),
                characteristicService.getComponentEntity(dto.drive_id()),
                characteristicService.getComponentEntity(dto.transmission_id()),
                characteristicService.getComponentEntity(dto.modification_id())
        ));
    }
    @Transactional(readOnly = true)
    public List<LazyAdvertisementResponse> getUserAdvertisements(String email) {
        Person user = getUserEntity(email);
        return user.getAdvertisements().stream().map(ad -> conversionService.convert(ad, LazyAdvertisementResponse.class)).toList();
    }
    @Transactional(readOnly = true)
    public boolean containsUserAdvertisements(long id, String email) {
        Person user = getUserEntity(email);
        Advertisement advertisement = advertisementService.getAdvertisementEntity(id);
        for(var ad : user.getAdvertisements()) {
            if(ad.getId() == advertisement.getId()) return true;
        }
        return false;
    }
    @Transactional(readOnly = true)
    public boolean containsUserFavourites(long id, String email) {
        Person user = getUserEntity(email);
        Advertisement advertisement = advertisementService.getAdvertisementEntity(id);
        for(var fv : user.getFavorites()) {
            if(fv.getId() == advertisement.getId()) return true;
        }
        return false;
    }
    public void addFavourite(long id, String email) {
        Person user = getUserEntity(email);
        user.addFavorite(advertisementService.getAdvertisementEntity(id));
    }
    public void deleteFavourite(long id, String email) {
        Person user = getUserEntity(email);
        Advertisement ad = getUserFavouriteEntity(id, user);
        user.getFavorites().remove(ad);
    }
    @Transactional(readOnly = true)
    public Advertisement getUserFavouriteEntity(long id, Person user) {
        return user.getFavorites().stream().filter(fv -> fv.getId() == id).findFirst().orElseThrow(EntityNotFoundException::new);
    }
    @Transactional(readOnly = true)
    public List<LazyAdvertisementResponse> getUserFavourites(String email) {
        Person user = getUserEntity(email);
        return user.getFavorites().stream().map(ad -> conversionService.convert(ad, LazyAdvertisementResponse.class)).toList();
    }
    @Transactional(readOnly = true)
    public EagerAdvertisementResponse getUserFavourite(long id, String email) {
        Person user = getUserEntity(email);
        Advertisement favourite = getUserFavouriteEntity(id, user);
        return conversionService.convert(favourite, EagerAdvertisementResponse.class);
    }
    @Transactional(readOnly = true)
    public boolean containsUserAdvertisementPhotos(long id, String email) {
        Person user = getUserEntity(email);
        AdvertisementPhoto photo = advertisementService.getAdvertisementPhotoEntity(id);
        for(var ad : user.getAdvertisements()) {
            for(var ph : ad.getPhotos()) {
                if(ph.getId() == photo.getId()) return true;
            }
        }
        return false;
    }

    private final PersonRepository personRepository;
    private final CharacteristicService characteristicService;
    private final MarkService markService;
    private final AdvertisementService advertisementService;
    private final ConversionService conversionService;
}

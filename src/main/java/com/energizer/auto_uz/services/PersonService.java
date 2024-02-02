package com.energizer.auto_uz.services;

import com.energizer.auto_uz.dto.reques.AdvertisementRequest;
import com.energizer.auto_uz.dto.reques.AdvertisementUpdateRequest;
import com.energizer.auto_uz.dto.reques.RegisterRequest;
import com.energizer.auto_uz.dto.response.EagerAdvertisementResponse;
import com.energizer.auto_uz.dto.response.LazyAdvertisementResponse;
import com.energizer.auto_uz.models.users.Advertisement;
import com.energizer.auto_uz.models.users.Person;
import com.energizer.auto_uz.repositories.AdvertisementRepository;
import com.energizer.auto_uz.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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
        return findByEmail(email).orElseThrow();
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
    public void updateAdvertisement(long id, AdvertisementUpdateRequest dto) {
        Advertisement advertisement = getAdvertisementEntity(id);
        if(dto.mileage() != null) advertisement.setMileage(dto.mileage());
        if(dto.description() != null) advertisement.setDescription(dto.description());
        if(dto.price() != null) advertisement.setPrice(dto.price());
        if(dto.generation_id() != null) advertisement.setGeneration(markService.getGenerationEntity(dto.generation_id()));
        if(dto.corpus_id() != null) advertisement.setCorpus(characteristicService.getComponentEntity(dto.corpus_id()));
        if(dto.engine_id() != null) advertisement.setEngine(characteristicService.getComponentEntity(dto.engine_id()));
        if(dto.drive_id() != null) advertisement.setDrive(characteristicService.getComponentEntity(dto.drive_id()));
        if(dto.transmission_id() != null) advertisement.setTransmission(characteristicService.getComponentEntity(dto.transmission_id()));
        if(dto.modification_id() != null) advertisement.setModification(characteristicService.getComponentEntity(dto.modification_id()));
    }
    public void deleteAdvertisement(Long id) {
        advertisementRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public Advertisement getAdvertisementEntity(long id) {
        return advertisementRepository.findById(id).orElseThrow();
    }
    @Transactional(readOnly = true)
    public EagerAdvertisementResponse getAdvertisement(long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElse(null);
        if(advertisement == null) return null;
        return conversionService.convert(advertisement, EagerAdvertisementResponse.class);
    }
    @Transactional(readOnly = true)
    public List<LazyAdvertisementResponse> getUserAdvertisements(String email) {
        Person user = getUserEntity(email);
        return user.getAdvertisements().stream().map(ad -> conversionService.convert(ad, LazyAdvertisementResponse.class)).toList();
    }
    @Transactional(readOnly = true)
    public boolean containAdvertisement(long id) {
        return advertisementRepository.findById(id).orElse(null) != null;
    }
    @Transactional(readOnly = true)
    public boolean containsUserAdvertisements(long id, String email) {
        Person user = getUserEntity(email);
        Advertisement advertisement = getAdvertisementEntity(id);
        for(var ad : user.getAdvertisements()) {
            if(ad.getId() == advertisement.getId()) return true;
        }
        return false;
    }
    @Transactional(readOnly = true)
    public boolean containsUserFavourites(long id, String email) {
        Person user = getUserEntity(email);
        Advertisement advertisement = getAdvertisementEntity(id);
        for(var fv : user.getFavorites()) {
            if(fv.getId() == advertisement.getId()) return true;
        }
        return false;
    }
    public void addFavourite(Long id, String email) {
        Person user = getUserEntity(email);
        user.addFavorite(getAdvertisementEntity(id));
    }
    public void deleteFavourite(Long id, String email) {
        Person user = getUserEntity(email);
        Advertisement ad = getUserFavouriteEntity(id, user);
        user.getFavorites().remove(ad);
    }
    @Transactional(readOnly = true)
    public Advertisement getUserFavouriteEntity(Long id, Person user) {
        return user.getFavorites().stream().filter(fv -> fv.getId() == id).findFirst().orElseThrow();
    }
    @Transactional(readOnly = true)
    public List<LazyAdvertisementResponse> getUserFavourites(String email) {
        Person user = getUserEntity(email);
        return user.getFavorites().stream().map(ad -> conversionService.convert(ad, LazyAdvertisementResponse.class)).toList();
    }
    @Transactional(readOnly = true)
    public EagerAdvertisementResponse getUserFavourite(Long id, String email) {
        try {
            Person user = getUserEntity(email);
            Advertisement favourite = getUserFavouriteEntity(id, user);
            return conversionService.convert(favourite, EagerAdvertisementResponse.class);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private final PersonRepository personRepository;
    private final AdvertisementRepository advertisementRepository;
    private final CharacteristicService characteristicService;
    private final MarkService markService;
    private final ConversionService conversionService;
}

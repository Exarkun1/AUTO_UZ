package com.energizer.auto_uz.services;

import com.energizer.auto_uz.dto.reques.AdvertisementUpdateRequest;
import com.energizer.auto_uz.dto.response.EagerAdvertisementResponse;
import com.energizer.auto_uz.exceptions.EntityNotFoundException;
import com.energizer.auto_uz.exceptions.FileNotExistException;
import com.energizer.auto_uz.models.advertisements.Advertisement;
import com.energizer.auto_uz.models.advertisements.AdvertisementPhoto;
import com.energizer.auto_uz.models.users.Person;
import com.energizer.auto_uz.repositories.AdvertisementPhotoRepository;
import com.energizer.auto_uz.repositories.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdvertisementService {
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
    public void deleteAdvertisement(long id) {
        advertisementRepository.deleteWithId(id);
    }
    @Transactional(readOnly = true)
    public Advertisement getAdvertisementEntity(long id) {
        return advertisementRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional(readOnly = true)
    public EagerAdvertisementResponse getAdvertisement(long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return conversionService.convert(advertisement, EagerAdvertisementResponse.class);
    }
    @Transactional(readOnly = true)
    public boolean containsAdvertisement(long id) {
        return advertisementRepository.findById(id).orElse(null) != null;
    }

    public void addAdvertisementPhotos(long id, List<String> filenames) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow();
        advertisement.addPhotos(filenames.stream().map(AdvertisementPhoto::new).toList());
    }
    public void deleteAdvertisementPhoto(long id) {
        AdvertisementPhoto photo = getAdvertisementPhotoEntity(id);
        photo.setAdvertisement(null);
    }
    @Transactional(readOnly = true)
    public AdvertisementPhoto getAdvertisementPhotoEntity(long id) {
        return advertisementPhotoRepository.findById(id).orElseThrow(FileNotExistException::new);
    }
    @Transactional(readOnly = true)
    public String getAdvertisementFilename(long id) {
        return getAdvertisementPhotoEntity(id).getFilename();
    }
    @Transactional(readOnly = true)
    public boolean containsAdvertisementPhoto(long id) {
        return advertisementPhotoRepository.findById(id).orElse(null) != null;
    }

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementPhotoRepository advertisementPhotoRepository;
    private final CharacteristicService characteristicService;
    private final MarkService markService;
    private final ConversionService conversionService;
}

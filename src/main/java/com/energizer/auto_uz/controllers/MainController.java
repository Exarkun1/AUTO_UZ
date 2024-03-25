package com.energizer.auto_uz.controllers;

import com.energizer.auto_uz.dto.response.*;
import com.energizer.auto_uz.services.AdvertisementService;
import com.energizer.auto_uz.services.CharacteristicService;
import com.energizer.auto_uz.services.MarkService;
import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.utils.FileStorageUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/get/characteristics")
    public List<CharacteristicResponse> getAllCharacteristics() {
        return characteristicService.getAllCharacteristics();
    }

    @GetMapping("/get/components/{type}")
    public List<ComponentResponse> getComponentsByType(@PathVariable("type") String type) {
        return characteristicService.getComponentsByType(type);
    }

    @GetMapping("/get/brands")
    public List<MarkResponse> getBrands() {
        return markService.getBrands();
    }

    @GetMapping("/get/models")
    public List<MarkResponse> getModels() {
        return markService.getModels();
    }

    @GetMapping("/get/generations")
    public List<MarkResponse> getGenerations() {
        return markService.getGenerations();
    }

    @GetMapping("/get/{brand_id}/models")
    public List<MarkResponse> getModels(@PathVariable("brand_id") Long brandId) {
        return markService.getModelsByBrandId(brandId);
    }

    @GetMapping("/get/{model_id}/generations")
    public List<MarkResponse> getGenerations(@PathVariable("model_id") Long modelId) {
        return markService.getGenerationsByModelId(modelId);
    }

    @GetMapping("/get/brand/{id}")
    public BrandResponse getBrand(@PathVariable("id") Long id) {
        return markService.getBrand(id);
    }

    @GetMapping("/get/model/{id}")
    public ModelResponse getModel(@PathVariable("id") Long id) {
        return markService.getModel(id);
    }

    @GetMapping("/get/generation/{id}")
    public GenerationResponse getGeneration(@PathVariable("id") Long id) {
        return markService.getGeneration(id);
    }

    @GetMapping("/get/advertisement/{id}")
    public EagerAdvertisementResponse getAdvertisement(@PathVariable("id") Long id) {
        return advertisementService.getAdvertisement(id);
    }

    @GetMapping("/get/advertisement_photo/{id}")
    public ResponseEntity<Resource> getAdvertisementPhoto(@PathVariable("id") Long id, HttpServletRequest request) {
        Resource resource = fileStorageUtil.loadFile(advertisementService.getAdvertisementFilename(id));
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/get/user_info/{id}")
    public PubliclyUserResponse getUserInfo(@PathVariable("id") Long id) {
        return personService.getPubliclyUser(id);
    }

    @GetMapping("/get/feedback/{id}")
    public FeedbackResponse getFeedback(@PathVariable("id") Long id) {
        return personService.getFeedback(id);
    }

    private final CharacteristicService characteristicService;
    private final MarkService markService;
    private final PersonService personService;
    private final AdvertisementService advertisementService;
    private final FileStorageUtil fileStorageUtil;
}

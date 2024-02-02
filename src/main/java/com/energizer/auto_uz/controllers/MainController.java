package com.energizer.auto_uz.controllers;

import com.energizer.auto_uz.dto.response.*;
import com.energizer.auto_uz.services.CharacteristicService;
import com.energizer.auto_uz.services.MarkService;
import com.energizer.auto_uz.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        return personService.getAdvertisement(id);
    }

    private final CharacteristicService characteristicService;
    private final MarkService markService;
    private final PersonService personService;
}

package com.energizer.auto_uz.services;

import com.energizer.auto_uz.dto.reques.BrandListRequest;
import com.energizer.auto_uz.dto.reques.GenerationListRequest;
import com.energizer.auto_uz.dto.reques.ModelListRequest;
import com.energizer.auto_uz.dto.response.BrandResponse;
import com.energizer.auto_uz.dto.response.GenerationResponse;
import com.energizer.auto_uz.dto.response.MarkResponse;
import com.energizer.auto_uz.dto.response.ModelResponse;
import com.energizer.auto_uz.exceptions.EntityNotFoundException;
import com.energizer.auto_uz.models.marks.Brand;
import com.energizer.auto_uz.models.marks.Generation;
import com.energizer.auto_uz.models.marks.Model;
import com.energizer.auto_uz.repositories.BrandRepository;
import com.energizer.auto_uz.repositories.GenerationRepository;
import com.energizer.auto_uz.repositories.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MarkService {
    @Transactional(readOnly = true)
    public boolean containsBrandId(long id) {
        return brandRepository.findById(id).orElse(null) != null;
    }
    public void addBrands(BrandListRequest dto) {
        dto.brands().forEach(b -> brandRepository.save(new Brand(b)));
    }
    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public List<MarkResponse> getBrands() {
        return brandRepository.findAll().stream()
                .map(b -> new MarkResponse(b.getId(), b.getName(), null)).toList();
    }
    @Transactional(readOnly = true)
    public BrandResponse getBrand(long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return conversionService.convert(brand, BrandResponse.class);
    }
    @Transactional(readOnly = true)
    public boolean containsModelId(long id) {
        return modelRepository.findById(id).orElse(null) != null;
    }
    public void addModels(ModelListRequest dto) {
        Brand brand = brandRepository.findById(dto.brand_id()).orElseThrow();
        brand.addModels(dto.models().stream().map(Model::new).toList());
    }
    public void deleteModel(long id) {
        modelRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public List<MarkResponse> getModels() {
        return modelRepository.findAll().stream()
                .map(m -> new MarkResponse(m.getId(), m.getName(), m.getBrand().getId())).toList();
    }
    @Transactional(readOnly = true)
    public List<MarkResponse> getModelsByBrandId(long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(EntityNotFoundException::new);
        return brand.getModels().stream()
                .map(m -> new MarkResponse(m.getId(), m.getName(), m.getBrand().getId())).toList();
    }
    @Transactional(readOnly = true)
    public ModelResponse getModel(long id) {
        Model model = modelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return conversionService.convert(model, ModelResponse.class);
    }
    @Transactional(readOnly = true)
    public boolean containsGenerationId(long id) {
        return generationRepository.findById(id).orElse(null) != null;
    }
    public void addGenerations(GenerationListRequest dto) {
        Model model = modelRepository.findById(dto.model_id()).orElseThrow();
        model.addGenerations(dto.generations().stream()
                .map(g -> conversionService.convert(g, Generation.class)).toList());
    }
    public void deleteGeneration(long id) {
        generationRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public List<MarkResponse> getGenerations() {
        return generationRepository.findAll().stream()
                .map(g -> new MarkResponse(g.getId(), g.getName(), g.getModel().getId())).toList();
    }
    @Transactional(readOnly = true)
    public List<MarkResponse> getGenerationsByModelId(long modelId) {
        Model model = modelRepository.findById(modelId).orElseThrow(EntityNotFoundException::new);
        return model.getGenerations().stream()
                .map(g -> new MarkResponse(g.getId(), g.getName(), g.getModel().getId())).toList();
    }
    @Transactional(readOnly = true)
    public Generation getGenerationEntity(long id) {
        return generationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional(readOnly = true)
    public GenerationResponse getGeneration(long id) {
        Generation generation = getGenerationEntity(id);
        return conversionService.convert(generation, GenerationResponse.class);
    }

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final GenerationRepository generationRepository;
    private final ConversionService conversionService;
}

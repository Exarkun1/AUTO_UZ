package com.energizer.auto_uz.controllers;

import com.energizer.auto_uz.dto.reques.BrandListRequest;
import com.energizer.auto_uz.dto.reques.ComponentListRequest;
import com.energizer.auto_uz.dto.errors.DataErrors;
import com.energizer.auto_uz.dto.reques.GenerationListRequest;
import com.energizer.auto_uz.dto.reques.ModelListRequest;
import com.energizer.auto_uz.dto.response.MarkResponse;
import com.energizer.auto_uz.exceptions.ObjectNotCreatedException;
import com.energizer.auto_uz.services.CharacteristicService;
import com.energizer.auto_uz.services.MarkService;
import com.energizer.auto_uz.utils.ErrorsUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    @PostMapping("/add/components")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComponents(@RequestBody @Valid ComponentListRequest dto, BindingResult errors) {
        if(errors.hasErrors()) {
            throw new ObjectNotCreatedException(errorsUtil.getDataErrors(errors));
        }
        characteristicService.addComponents(dto);
    }

    @DeleteMapping("/delete/component/{id}")
    public void deleteComponent(@PathVariable("id") Long id) {
        characteristicService.deleteComponent(id);
    }

    @PostMapping("/add/brands")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBrands(@RequestBody @Valid BrandListRequest brands, BindingResult errors) {
        if(errors.hasErrors()) {
            throw new ObjectNotCreatedException(errorsUtil.getDataErrors(errors));
        }
        markService.addBrands(brands);
    }
    @DeleteMapping("/delete/brand/{id}")
    public void deleteBrand(@PathVariable("id") Long id) {
        markService.deleteBrand(id);
    }

    @PostMapping("/add/models")
    @ResponseStatus(HttpStatus.CREATED)
    public void addModels(@RequestBody @Valid ModelListRequest models, BindingResult errors) {
        if(errors.hasErrors()) {
            throw new ObjectNotCreatedException(errorsUtil.getDataErrors(errors));
        }
        markService.addModels(models);
    }

    @DeleteMapping("/delete/model/{id}")
    public void deleteModel(@PathVariable("id") Long id) {
        markService.deleteModel(id);
    }

    @PostMapping("/add/generations")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGenerations(@RequestBody @Valid GenerationListRequest generations, BindingResult errors) {
        if(errors.hasErrors()) {
            throw new ObjectNotCreatedException(errorsUtil.getDataErrors(errors));
        }
        markService.addGenerations(generations);
    }

    @DeleteMapping("/delete/generation/{id}")
    public void deleteGeneration(@PathVariable("id") Long id) {
        markService.deleteGeneration(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private DataErrors badCredentials(ObjectNotCreatedException e) {
        return e.getErrors();
    }

    private final CharacteristicService characteristicService;
    private final MarkService markService;
    private final ErrorsUtil errorsUtil;
}

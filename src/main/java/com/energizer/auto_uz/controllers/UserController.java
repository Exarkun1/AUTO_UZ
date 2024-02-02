package com.energizer.auto_uz.controllers;

import com.energizer.auto_uz.dto.errors.DataErrors;
import com.energizer.auto_uz.dto.reques.AdvertisementRequest;
import com.energizer.auto_uz.dto.reques.AdvertisementUpdateRequest;
import com.energizer.auto_uz.dto.response.EagerAdvertisementResponse;
import com.energizer.auto_uz.dto.response.LazyAdvertisementResponse;
import com.energizer.auto_uz.exceptions.ObjectNotCreatedException;
import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.utils.ErrorsUtil;
import com.energizer.auto_uz.validation.annotatons.CurrentUserAdvertisementId;
import com.energizer.auto_uz.validation.annotatons.CurrentUserFavouriteId;
import com.energizer.auto_uz.validation.annotatons.CurrentUserNewFavouriteId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/get/advertisements")
    public List<LazyAdvertisementResponse> getAdvertisements(Principal principal) {
        return personService.getUserAdvertisements(principal.getName());
    }
    @PostMapping("/add/advertisement")
    public void addAdvertisement(@RequestBody @Valid AdvertisementRequest advertisement, BindingResult errors, Principal principal) {
        if(errors.hasErrors()) {
            throw new ObjectNotCreatedException(errorsUtil.getDataErrors(errors));
        }
        personService.addAdvertisement(principal.getName(), advertisement);
    }
    @PatchMapping("/update/advertisement/{id}")
    public void updateAdvertisement(@RequestBody @Valid AdvertisementUpdateRequest advertisement, BindingResult errors,
                                    @PathVariable("id") @Valid @CurrentUserAdvertisementId Long id) {
        if(errors.hasErrors()) {
            throw new ObjectNotCreatedException(errorsUtil.getDataErrors(errors));
        }
        personService.updateAdvertisement(id, advertisement);
    }
    @DeleteMapping("/delete/advertisement/{id}")
    public void deleteAdvertisement(@PathVariable("id") @Valid @CurrentUserAdvertisementId Long id) {
        personService.deleteAdvertisement(id);
    }
    @GetMapping("/get/favourites")
    public List<LazyAdvertisementResponse> getFavourites(Principal principal) {
        return personService.getUserFavourites(principal.getName());
    }
    @GetMapping("/get/favourite/{id}")
    public EagerAdvertisementResponse getFavourite(@PathVariable("id") Long id, Principal principal) {
        return personService.getUserFavourite(id, principal.getName());
    }
    @PostMapping("/add/favourite/{id}")
    public void addFavourite(@PathVariable("id") @Valid @CurrentUserNewFavouriteId Long id, Principal principal) {
        personService.addFavourite(id, principal.getName());
    }
    @DeleteMapping("/delete/favourite/{id}")
    public void deleteFavourite(@PathVariable("id") @Valid @CurrentUserFavouriteId Long id, Principal principal) {
        personService.deleteFavourite(id, principal.getName());
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private DataErrors badCredentials(ObjectNotCreatedException e) {
        return e.getErrors();
    }

    private final PersonService personService;
    private final ErrorsUtil errorsUtil;
}

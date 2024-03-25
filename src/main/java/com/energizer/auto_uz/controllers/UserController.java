package com.energizer.auto_uz.controllers;

import com.energizer.auto_uz.dto.errors.DataErrors;
import com.energizer.auto_uz.dto.reques.AdvertisementRequest;
import com.energizer.auto_uz.dto.reques.AdvertisementUpdateRequest;
import com.energizer.auto_uz.dto.reques.FeedbackRequest;
import com.energizer.auto_uz.dto.response.EagerAdvertisementResponse;
import com.energizer.auto_uz.dto.response.LazyAdvertisementResponse;
import com.energizer.auto_uz.exceptions.ObjectNotCreatedException;
import com.energizer.auto_uz.services.AdvertisementService;
import com.energizer.auto_uz.services.PersonService;
import com.energizer.auto_uz.utils.ErrorsUtil;
import com.energizer.auto_uz.utils.FileStorageUtil;
import com.energizer.auto_uz.validation.annotatons.*;
import com.energizer.auto_uz.validation.annotatons.id.*;
import com.energizer.auto_uz.validation.annotatons.id.UniqueFeedback;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                                    @PathVariable("id") @Valid @CurrentAdvertisementId Long id) {
        if(errors.hasErrors()) {
            throw new ObjectNotCreatedException(errorsUtil.getDataErrors(errors));
        }
        advertisementService.updateAdvertisement(id, advertisement);
    }

    @DeleteMapping("/delete/advertisement/{id}")
    public void deleteAdvertisement(@PathVariable("id") @Valid @CurrentAdvertisementId Long id) {
        advertisementService.deleteAdvertisement(id);
    }

    @PostMapping("/add/{advertisement_id}/advertisement_photos")
    public void addAdvertisementPhotos(@RequestParam("files") @Valid @IsImageList List<MultipartFile> files,
                                       @PathVariable("advertisement_id") @Valid @CurrentAdvertisementId Long id) {
        List<String> filenames = fileStorageUtil.saveFiles(files);
        advertisementService.addAdvertisementPhotos(id, filenames);
    }

    @DeleteMapping("/delete/advertisement_photo/{id}")
    public void deleteAdvertisementPhoto(@PathVariable("id") @Valid @CurrentAdvertisementPhotoId Long id) {
        advertisementService.deleteAdvertisementPhoto(id);
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
    public void addFavourite(@PathVariable("id") @Valid @CurrentAdvertisementId(value = false) @CurrentFavouriteId(value = false) Long id,
                             Principal principal) {
        personService.addFavourite(id, principal.getName());
    }

    @DeleteMapping("/delete/favourite/{id}")
    public void deleteFavourite(@PathVariable("id") @Valid @CurrentFavouriteId Long id, Principal principal) {
        personService.deleteFavourite(id, principal.getName());
    }

    @PostMapping("/add/feedback/{user_id}")
    public void addFeedback(@RequestBody @Valid FeedbackRequest feedback, BindingResult errors,
                            @PathVariable("user_id") @Valid @CurrentUserId(value = false) @UniqueFeedback Long id, Principal principal) {
        if(errors.hasErrors()) {
            throw new ObjectNotCreatedException(errorsUtil.getDataErrors(errors));
        }
        personService.addFeedback(id, feedback, principal.getName());
    }

    @DeleteMapping("/delete/feedback/{id}")
    public void deleteFeedback(@PathVariable("id") @Valid @CurrentFeedbackId Long id) {
        personService.deleteFeedback(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private DataErrors badCredentials(ObjectNotCreatedException e) {
        return e.getErrors();
    }

    private final PersonService personService;
    private final AdvertisementService advertisementService;
    private final ErrorsUtil errorsUtil;
    private final FileStorageUtil fileStorageUtil;
}

package com.energizer.auto_uz.services;

import com.energizer.auto_uz.dto.reques.ComponentListRequest;
import com.energizer.auto_uz.dto.response.ComponentResponse;
import com.energizer.auto_uz.dto.response.CharacteristicResponse;
import com.energizer.auto_uz.models.characteristics.Characteristic;
import com.energizer.auto_uz.models.characteristics.ComponentEntity;
import com.energizer.auto_uz.repositories.CharacteristicRepository;
import com.energizer.auto_uz.repositories.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CharacteristicService {
    @Transactional(readOnly = true)
    public List<CharacteristicResponse> getAllCharacteristics() {
        return characteristicRepository.findAll().stream().map(c -> new CharacteristicResponse(c.getId(), c.getType())).toList();
    }
    @Transactional(readOnly = true)
    public boolean containsCharacteristic(String type) {
        for(var ch : characteristicRepository.findAll()) {
            if(ch.getType().equals(type)) return true;
        }
        return false;
    }
    public Characteristic getByType(String type) {
        return characteristicRepository.findByType(type).orElseThrow();
    }
    public List<ComponentEntity> getAllComponentEntities() {
        return componentRepository.findAll();
    }

    public void addComponents(ComponentListRequest dto) {
        Characteristic characteristic = characteristicRepository.findByType(dto.type()).orElseThrow();
        characteristic.addComponents(dto.components().stream().map(ComponentEntity::new).toList());
        characteristicRepository.save(characteristic);
    }
    @Transactional(readOnly = true)
    public List<ComponentResponse> getComponentsByType(String type) {
        return componentRepository.findWhereTypeIs(type).stream().map(c -> new ComponentResponse(c.getId(), c.getName())).toList();
    }
    public void deleteComponent(long id) {
        componentRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public boolean containComponentByType(long id, String type) {
        return componentRepository.findWhereIdAndTypeIs(id, type).orElse(null) != null;
    }
    @Transactional(readOnly = true)
    public ComponentEntity getComponentEntity(long id) {
        return componentRepository.findById(id).orElse(null);
    }

    private final CharacteristicRepository characteristicRepository;
    private final ComponentRepository componentRepository;
}

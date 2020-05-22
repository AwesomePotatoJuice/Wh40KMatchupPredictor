package entity;

import entity.components.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BaseUnitEntity {
    private BaseAbilities abilities;
    private BaseCharacteristics characteristics;
    private BaseFactionKeywords factionKeywords;
    private BaseWeapon weapon;
    private BaseKeywords keywords;
    private Integer pointValue;
}

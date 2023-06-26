package com.bank.sbnz.DTO;

import com.bank.sbnz.enums.PackageAccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.rule.All;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPackageDTO {
    private Long id;
    private PackageAccountType type;
}

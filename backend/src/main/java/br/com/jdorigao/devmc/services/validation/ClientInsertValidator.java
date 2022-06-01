package br.com.jdorigao.devmc.services.validation;

import br.com.jdorigao.devmc.controllers.exceptions.FieldMessage;
import br.com.jdorigao.devmc.dto.ClientNewDTO;
import br.com.jdorigao.devmc.entities.enums.TypeClient;
import br.com.jdorigao.devmc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().equals(TypeClient.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CPF invalid"));
        }

        if (objDto.getType().equals(TypeClient.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ invalid"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}

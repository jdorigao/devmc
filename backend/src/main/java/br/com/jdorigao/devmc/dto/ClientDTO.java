package br.com.jdorigao.devmc.dto;

import br.com.jdorigao.devmc.entities.Client;
import br.com.jdorigao.devmc.services.validation.ClientUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientUpdate
public class ClientDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "Name is required")
    @Length(min = 5, max = 80, message = "Name must be between 5 and 80 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Client obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

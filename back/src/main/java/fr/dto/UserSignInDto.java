package fr.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInDto {
    private Integer id;

    @NotBlank(message = "Le nom de famille est requis")
    @Size(max = 50, message = "Le nom de famille ne peut pas dépasser 50 caractères")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s-]*$", message = "Le nom de famille doit contenir uniquement des lettres, espaces ou tirets")
    private String lastname;

    @NotBlank(message = "Le prénom est requis")
    @Size(max = 50, message = "Le prénom ne peut pas dépasser 50 caractères")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s-]*$", message = "Le prénom doit contenir uniquement des lettres, espaces ou tirets")
    private String firstname;

    @NotBlank(message = "L'adresse email est requise")
    @Email(message = "L'adresse email doit être valide")
    @Size(max = 100, message = "L'adresse email ne peut pas dépasser 100 caractères")
    private String email;

    @NotBlank(message = "Le mot de passe est requis")
    @Size(min = 8, message = "Le mot de passe doit avoir au moins 8 caractères")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$", message = "Le mot de passe doit contenir au moins une majuscule, un chiffre et avoir une longueur minimale de 8 caractères")
    private String password;

    @NotBlank(message = "Le numéro est requis")
    @Pattern(regexp = "^0\\d{9}$", message = "Le numéro de téléphone doit être au format valide")
    private String phone;

    private Long creationDate;
    private List<RoleDto> roles;
    private byte[] image;
}

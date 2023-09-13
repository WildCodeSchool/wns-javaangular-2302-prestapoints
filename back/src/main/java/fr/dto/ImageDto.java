package fr.dto;

import fr.entity.Prestation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {

    private Integer id;
    private byte[] data;
    private Prestation prestation;
}

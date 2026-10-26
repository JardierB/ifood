package github.com.jardierB.dto;

import org.mapstruct.Mapper;

import github.com.jardierB.model.Restaurante;
import jakarta.enterprise.inject.Default;

@Mapper (componentModel = "cdi")
public interface RestauranteMapper {

    @Default 
    public Restaurante toRestaurante(AdicionarRestauranteDTO dto);

}

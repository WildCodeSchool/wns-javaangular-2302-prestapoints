package fr.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.dto.ProviderDto;
import fr.model.Registration;

@Component
public class ProviderMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProviderDto convertToDto(Registration provider) {
        ProviderDto providerDto = modelMapper.map(provider, ProviderDto.class);

        return providerDto;
    }

    public Registration convertToEntity(ProviderDto providerDto) {
        Registration provider = modelMapper.map(providerDto, Registration.class);

        return provider;
    }
}

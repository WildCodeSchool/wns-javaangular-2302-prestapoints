package fr.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.dto.ProviderDto;
import fr.entity.Provider;

@Component
public class ProviderMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProviderDto convertToDto(Provider provider) {
        ProviderDto providerDto = modelMapper.map(provider, ProviderDto.class);

        return providerDto;
    }

    public Provider convertToEntity(ProviderDto providerDto) {
        Provider provider = modelMapper.map(providerDto, Provider.class);

        return provider;
    }
}

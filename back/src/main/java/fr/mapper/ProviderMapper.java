package fr.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.dto.ProviderDto;
import fr.model.Provider;

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

    public List<ProviderDto> convertAllToDto(List<Provider> providers) {
        List<ProviderDto> providersDto = new ArrayList<>();
        
        for (Provider provider : providers) {
            providersDto.add(modelMapper.map(provider, ProviderDto.class));
        }

        return providersDto;
    }
}

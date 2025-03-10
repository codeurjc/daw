package es.codeurjc.daw.library.service;

import java.sql.SQLException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.library.dto.ShopBasicDTO;
import es.codeurjc.daw.library.dto.ShopMapper;
import es.codeurjc.daw.library.model.Shop;
import es.codeurjc.daw.library.repository.ShopRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopMapper mapper;

    public Collection<ShopBasicDTO> getShops() {

        return toDTOs(shopRepository.findAll());
    }

    public ShopBasicDTO getShop(long id) {

        return toDTO(shopRepository.findById(id).orElseThrow());
    }

    public ShopBasicDTO createShop(ShopBasicDTO shopDTO) {

        if(shopDTO.id() != null) {
            throw new IllegalArgumentException();
        }

        Shop shop = toDomain(shopDTO);

        shop = shopRepository.save(shop);

        return toDTO(shop);
    }

    public ShopBasicDTO replaceShop(long id, ShopBasicDTO updatedShopDTO) throws SQLException {

        shopRepository.findById(id).orElseThrow();

        Shop updatedShop = toDomain(updatedShopDTO);
        updatedShop.setId(id);

        shopRepository.save(updatedShop);

        return toDTO(updatedShop);
    }

    public ShopBasicDTO createOrReplaceShop(Long id, ShopBasicDTO shopDTO) throws SQLException {
        
        ShopBasicDTO shop;
        if(id == null) {
            shop = createShop(shopDTO);
        } else {
            shop = replaceShop(id, shopDTO);
        }
        return shop;
    }

    public ShopBasicDTO deleteShop(long id) {

        Shop shop = shopRepository.findById(id).orElseThrow();

        shopRepository.deleteById(id);

        return toDTO(shop);
    }

    private ShopBasicDTO toDTO(Shop shop) {
        return mapper.toDTO(shop);
    }

    private Shop toDomain(ShopBasicDTO shopDTO) {
        return mapper.toDomain(shopDTO);
    }

    private Collection<ShopBasicDTO> toDTOs(Collection<Shop> shops) {
        return mapper.toDTOs(shops);
    }

}

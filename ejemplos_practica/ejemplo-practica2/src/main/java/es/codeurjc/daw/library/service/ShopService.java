package es.codeurjc.daw.library.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.library.model.Shop;
import es.codeurjc.daw.library.repository.ShopRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public Collection<Shop> getShops() {

        return shopRepository.findAll();
    }

    public Shop getShop(long id) {

        return shopRepository.findById(id).orElseThrow();
    }

    public List<Shop> getShops(List<Long> ids) {

        return shopRepository.findAllById(ids);
    }

    public Shop createShop(Shop shop) {

        if (shop.getId() != null) {
            throw new IllegalArgumentException();
        }

        shop = shopRepository.save(shop);

        return shop;
    }

    public Shop replaceShop(long id, Shop updatedShop) throws SQLException {

        shopRepository.findById(id).orElseThrow();

        updatedShop.setId(id);

        shopRepository.save(updatedShop);

        return updatedShop;
    }

    public Shop deleteShop(long id) {

        Shop shop = shopRepository.findById(id).orElseThrow();

        shopRepository.deleteById(id);

        return shop;
    }

}

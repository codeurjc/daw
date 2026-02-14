package es.codeurjc.daw.library.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.codeurjc.daw.library.dto.ShopBasicDTO;
import es.codeurjc.daw.library.dto.ShopMapper;
import es.codeurjc.daw.library.model.Shop;
import es.codeurjc.daw.library.service.ShopService;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/shops")
public class ShopRestController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopMapper shopMapper;

    @GetMapping("/")
    public Collection<ShopBasicDTO> getShops() {

        return shopMapper.toDTOs(shopService.getShops());
    }

    @GetMapping("/{id}")
    public ShopBasicDTO getShop(@PathVariable long id) {

        return shopMapper.toDTO(shopService.getShop(id));
    }

    @PostMapping("/")
    public ResponseEntity<ShopBasicDTO> createShop(@RequestBody ShopBasicDTO shopDTO) {

        Shop shop = shopMapper.toDomain(shopDTO);
        shop = shopService.createShop(shop);

        //As Shop is related to other entities, we need to reload it to get the related entities
        shop = shopService.getShop(shop.getId());
        shopDTO = shopMapper.toDTO(shop);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(shopDTO.id()).toUri();

        return ResponseEntity.created(location).body(shopDTO);
    }

    @PutMapping("/{id}")
    public ShopBasicDTO replaceShop(@PathVariable long id, @RequestBody ShopBasicDTO updatedShopDTO) throws SQLException {

        Shop updatedShop = shopMapper.toDomain(updatedShopDTO);
        updatedShop = shopService.replaceShop(id, updatedShop);
        return shopMapper.toDTO(updatedShop);
    }

    @DeleteMapping("/{id}")
    public ShopBasicDTO deleteShop(@PathVariable long id) {

        return shopMapper.toDTO(shopService.deleteShop(id));
    }
   
}

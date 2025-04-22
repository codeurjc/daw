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
import es.codeurjc.daw.library.service.ShopService;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/shops")
public class ShopRestController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/")
    public Collection<ShopBasicDTO> getShops() {

        return shopService.getShops();
    }

    @GetMapping("/{id}")
    public ShopBasicDTO getShop(@PathVariable long id) {

        return shopService.getShop(id);
    }

    @PostMapping("/")
    public ResponseEntity<ShopBasicDTO> createShop(@RequestBody ShopBasicDTO shopDTO) {

        shopDTO = shopService.createShop(shopDTO);

        //As Shop is related to other entities, we need to reload it to get the related entities
        shopDTO = shopService.getShop(shopDTO.id());

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(shopDTO.id()).toUri();

        return ResponseEntity.created(location).body(shopDTO);
    }

    @PutMapping("/{id}")
    public ShopBasicDTO replaceShop(@PathVariable long id, @RequestBody ShopBasicDTO updatedShopDTO) throws SQLException {

        return shopService.replaceShop(id, updatedShopDTO);
    }

    @DeleteMapping("/{id}")
    public ShopBasicDTO deleteShop(@PathVariable long id) {

        return shopService.deleteShop(id);
    }
   
}

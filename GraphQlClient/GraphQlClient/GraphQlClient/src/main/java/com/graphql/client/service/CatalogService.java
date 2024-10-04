package com.graphql.client.service;

import com.graphql.client.client.InventoryClient;
import com.graphql.client.dto.InputRequestDto;
import com.graphql.client.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    @Autowired
    private InventoryClient inventoryClient;

    public List<Item> viewProducts() {
        return inventoryClient.viewProducts();
    }

    public List<Item> viewProductsByCategory(String category) {
        return inventoryClient.viewProducts(category);
    }

    public Item receiveNewShipment(InputRequestDto itemRequest) {
        return inventoryClient.recieveNewShipment(itemRequest);
    }
}

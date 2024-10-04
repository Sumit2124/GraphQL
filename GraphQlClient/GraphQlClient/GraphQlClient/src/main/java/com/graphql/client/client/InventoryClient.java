package com.graphql.client.client;

import com.graphql.client.dto.InputRequestDto;
import com.graphql.client.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryClient
{
    @Autowired
    public HttpGraphQlClient graphQlClient;
    public List<Item> viewProducts()
    {
        String graphQlQuery="query GetProducts {\n" +
                "    getProducts {\n" +
                "        name\n" +
                "        price\n" +
                "    }\n" +
                "}\n";
        List<Item> products = graphQlClient.document(graphQlQuery).retrieve("getProducts").toEntityList(Item.class).block();
        return products;

    }
    public List<Item> viewProducts(String category)
    {
        String query=String.format("query GetProductsByCategory {\n" +
                "    getProductsByCategory(category: \"%s\") {\n" +
                "        id\n" +
                "        name\n" +
                "        category\n" +
                "    }\n" +
                "}\n",category);
        return graphQlClient.document(query).retrieve("getProductsByCategory").toEntityList(Item.class).block();
    }
    public Item recieveNewShipment(InputRequestDto input)
    {
        String graphQlQuery=String.format("mutation RecieveNewShipment {\n" +
                "    recieveNewShipment(id: \"%s\", quantity: %d) {\n" +
                "        id\n" +
                "        name\n" +
                "        price\n" +
                "        stock\n" +
                "    }\n" +
                "}\n",input.getId(),input.getQty());
        Item item = graphQlClient.document(graphQlQuery).retrieve("recieveNewShipment").toEntity(Item.class).block();
        return item;
    }
}

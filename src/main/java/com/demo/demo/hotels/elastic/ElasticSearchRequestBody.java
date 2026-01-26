package com.demo.demo.hotels.elastic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class ElasticSearchRequestBody {
    public static String getQueryObj(String hint,
                                     Integer page,
                                     Integer size)
            throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode matchNode = mapper.createObjectNode();
        matchNode.put("query", hint);

        ObjectNode queryNode = mapper.createObjectNode();
        queryNode.set("query_string",matchNode);

        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set("query",queryNode);

        if (page != null && page != 1) {
            rootNode.put("from", page * size);
        }

        if (size != null) {
            rootNode.put("size", size);
        }

        return mapper.writeValueAsString(rootNode);
    }

}

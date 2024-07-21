package com.example.userService.deserializer;

import com.example.userService.entities.UserInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserInfoDeserializer implements Deserializer<UserInfoDTO> {
    @Override
    public void close(){
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
    }

    @Override
    public UserInfoDTO deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();

        UserInfoDTO user = null;
        try {
            user = mapper.readValue(arg1, UserInfoDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to Deserialize");
        }
        return user;
    }
}

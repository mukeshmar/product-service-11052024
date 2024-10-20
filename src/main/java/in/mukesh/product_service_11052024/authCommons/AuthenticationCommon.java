package in.mukesh.product_service_11052024.authCommons;

import in.mukesh.product_service_11052024.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommon {
    private RestTemplate restTemplate;

    public AuthenticationCommon(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token){

        UserDto userDto = restTemplate.getForObject(
                "http://UserService/users/validate/" + token,
                UserDto.class
        );

        return userDto;
    }
}

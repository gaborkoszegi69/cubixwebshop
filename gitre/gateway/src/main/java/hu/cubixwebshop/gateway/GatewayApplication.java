package hu.cubixwebshop.gateway;

import hu.cubixwebshop.tokenlib.JwtService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.cubixwebshop.tokenlib.JwtAuthFilter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        scanBasePackageClasses = {JwtService.class, GatewayApplication.class}
)
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
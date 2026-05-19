package br.com.fh.comprasfh.pedidos.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "br.com.fh.comprasfh.pedidos.client")
public class ClientConfig {
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DosChingones;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author XPC
 */
@Configuration
public class Configuracion implements WebMvcConfigurer {

    //LocaleResolver ubica la informacion del browser local del usuario y fija la informacion a desplegar
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    //Define cual sera la variable que cambia el idioma de los textos.
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    //Se agrega un interceptor para poder hacer el cambio de idioma de inmediato
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/index").setViewName("index");
        registro.addViewController("/login").setViewName("/usuario/inicioU");
        registro.addViewController("/cuenta/nuevo").setViewName("/usuario/nuevoU");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/**", "/index",
                        "/menu/**",
                        "/registro/**",
                        "/js/**",
                        "/css/**",
                        "/webjars/**",
                        "/cuenta/nuevo",
                        "/menu_regular",
                        "/menu_vegano",
                        "/bebidas",
                        "/ofertas",
                        "/login",
                        "/sobre_nosotros").permitAll()
                .requestMatchers(
                        "/admin/usuario/**",
                        "/admin/categoria/**",
                        "/admin/platillo/**",
                        "/admin/pedido/**"
                ).hasRole("ADMIN")
                .requestMatchers(
                        "/admin/listadoUsuarios",
                        "/admin/listadoCategorias",
                        "/admin/listadoPlatillos",
                        "/admin/listadoPedidos",
                        "/admin/pedido/guardar"
                ).hasAnyRole("ADMIN", "EMPLEADO")
                .requestMatchers(
                        "/carrito/**",
                        "/cuenta/modifica",
                        "/cuenta/guardar",
                        "/cuenta/eliminar",
                        "/platillo/**",
                        "/pedidos/**")
                .hasRole("USER")
        )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/"));
        return http.build();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}

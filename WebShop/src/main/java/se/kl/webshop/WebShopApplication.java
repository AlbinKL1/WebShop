package se.kl.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO                              FÖR VG!
//TODO Fixa databasen så att items i order complete inte sparas som string utan pekar på produkt id i product table.
//TODO Lägg till email sändingen.
//TODO Fixa cartController så att det inte är så mycket kod.
//TODO Kanske ge security saken en till chans.

@SpringBootApplication
public class WebShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }
}

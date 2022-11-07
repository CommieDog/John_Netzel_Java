package com.company.gamestoreinvoicing.controller;

import com.company.gamestoreinvoicing.viewModel.ConsoleViewModel;
import com.company.gamestoreinvoicing.viewModel.GameViewModel;
import com.company.gamestoreinvoicing.viewModel.TShirtViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "gamestore-catalog")
public interface CatalogFeignClient {
    @GetMapping("/game/{id}")
    public GameViewModel getGameById(@PathVariable Long id);
    @GetMapping("/console/{id}")
    public ConsoleViewModel getConsoleById(@PathVariable Long id);
    @GetMapping("/tshirt/{id}")
    public TShirtViewModel getTShirtById(@PathVariable Long id);
}
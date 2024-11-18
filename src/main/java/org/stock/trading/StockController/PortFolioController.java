package org.stock.trading.StockController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class PortFolioController {

    @GetMapping
    public String getPortfolio(@RequestParam String userId){
        return "Hi this is from user "+ userId;
    }
}

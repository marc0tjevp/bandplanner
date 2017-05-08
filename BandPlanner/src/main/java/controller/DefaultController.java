package controller;

import datalayer.ArtistDAO;
import datalayer.PerformanceDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author maxim
 */
@Controller
public class DefaultController {

    ArtistDAO artistDAO = ArtistDAO.getInstance();
    PerformanceDAO performanceDAO = PerformanceDAO.getInstance();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {

        map.put("getAllArtists", artistDAO.getAllArtists());
        map.put("getAllPerformances", performanceDAO.getAllPerformances());

        return "index";
    }

}

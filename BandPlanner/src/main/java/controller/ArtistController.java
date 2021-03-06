package controller;

import datalayer.ArtistDAO;
import datalayer.PerformanceDAO;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import model.Artist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author maxim
 */
@Controller("ArtistC")
public class ArtistController {

    ArtistDAO artistDAO = ArtistDAO.getInstance();

    // Add Artist
    @RequestMapping(value = "/addArtist", method = RequestMethod.POST)
    public ModelAndView addArtist(HttpServletRequest request) {

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Artist a = new Artist(name, description);

        artistDAO.createArtist(a);

        return new ModelAndView("redirect:/");
    }

    // Update Artist
    @RequestMapping(value = "/updateArtist", method = RequestMethod.POST)
    public ModelAndView updateArtist(HttpServletRequest request) {

        String id = request.getParameter("artistId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Artist a = artistDAO.getArtistById(UUID.fromString(id));

        a.setName(name);
        a.setDescription(description);

        artistDAO.updateArtist(a);

        return new ModelAndView("redirect:/");
    }

    // Delete Artist 
    @RequestMapping(value = "/deleteArtist", method = RequestMethod.GET)
    public ModelAndView deleteArtist(HttpServletRequest request) {

        UUID artistID = UUID.fromString(request.getParameter("id"));
        artistDAO.deleteArtist(artistDAO.getArtistById(artistID));

        return new ModelAndView("redirect:/");
    }

    // Get Artist Info
    @RequestMapping(value = "/viewArtist", method = RequestMethod.GET)
    public String viewArtist(HttpServletRequest request, ModelMap map) {

        PerformanceDAO performanceDAO = PerformanceDAO.getInstance();

        UUID artistID = UUID.fromString(request.getParameter("id"));

        map.put("thisArtist", artistDAO.getArtistById(artistID));
        map.put("performancesByArtist", performanceDAO.getPerformancesByArtist(artistDAO.getArtistById(artistID)));

        return ("viewArtist");
    }

}

package it.unisa.offerta_formativa.moodle.manager;

import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleCategory;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleCallRestWebService;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestCourse;
import it.unisa.offerta_formativa.moodle.moodle_rest.MoodleRestException;
import it.unisa.offerta_formativa.moodle.moodle_rest.OptionParameter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davide
 *
 */
public class MoodleCategoryManager {

    /**
     * instance of this class, obtainable only by @getInstance()
     */
    private static MoodleCategoryManager instance = null;

    private MoodleCategoryManager(String urlMoodle, String token) {
        MoodleCallRestWebService.init(urlMoodle, token, false);
    }

    /**
     * Singleton Pattern
     *
     * @return the unique instance of this class
     */
    public static MoodleCategoryManager getInstance(String urlMoodle, String token) {
        if (instance == null) {
            instance = new MoodleCategoryManager(urlMoodle, token);
        }
        return instance;
    }

    /**
     * Create Degree Category into the Moodle Database
     *
     * @param title is the title of Degree to be inserted
     * @param cycle is the id of parent into the Moodle Database
     * @return true if all goes well
     */
    public boolean createCategory(String title, int parent) {
        try {
            MoodleRestCourse.createCategory(new MoodleCategory(title, Long.parseLong(String.valueOf(parent))));
            return true;
        } catch (UnsupportedEncodingException | MoodleRestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        // TODO Auto-generated method stub
    }

    /**
     * Delete Degree Category into the Moodle Database
     *
     * @param title is the title of Degree to be deleted
     * @param cycle is the id of Degree Category into the Moodle Database
     * @return true if all goes well
     */
    public boolean deleteCategory(String title, int cycle) {
        try {
            MoodleRestCourse.deleteCategory(new MoodleCategory(Long.parseLong(String.valueOf(cycle)), title));
            return true;
        } catch (UnsupportedEncodingException | MoodleRestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update Degree Category into the Moodle Database
     *
     * @param title is the title of Degree to be inserted
     * @param cycle is the id of Degree Category into the Moodle Database
     * @return true if all goes well
     */
    public boolean updateCategory(String title, int cycle) {
        try {
            MoodleRestCourse.updateCategory(new MoodleCategory(Long.parseLong(String.valueOf(cycle)), title));
            return true;
        } catch (UnsupportedEncodingException | MoodleRestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

		// TODO Auto-generated method stub
    }

    /**
     * Get Id of Degree by name
     *
     * @param name
     * @return Id or -1 if doesn't exists
     */
    public int getIdCategory(String name) {
        Long id = (long) 0;
        try {
            MoodleCategory[] categories = MoodleRestCourse.getCategories();
            for (int i = 0; i < categories.length; i++) {
                if (categories[i].getName().equalsIgnoreCase(name)) {
                    id = categories[i].getId();
                }
            }
            return Integer.parseInt(String.valueOf(id));
        } catch (UnsupportedEncodingException | MoodleRestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
        // TODO Auto-generated method stub
    }

    public int getIdCategoryByParent(String cycle,String degreeTitle) throws MoodleRestException {
        int id = getIdCategory(cycle);
        Long idDegree = null;
        OptionParameter categorySearch = new OptionParameter("parent", id+"");
        try {
            MoodleCategory[] categories = MoodleRestCourse.getCategories(categorySearch);
            for (int i = 0; i < categories.length; i++) {
            if (categories[i].getName().equalsIgnoreCase(degreeTitle)) {
                idDegree = categories[i].getId();
            }
        }
        } catch (UnsupportedEncodingException ex) {
            out.print("<h1>Moodle Exception: </h1>" + ex.getMessage());
        }
        return Integer.parseInt(String.valueOf(idDegree));
        // TODO Auto-generated method stub
    }

}

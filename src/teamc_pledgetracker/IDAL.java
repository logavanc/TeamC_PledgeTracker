package teamc_pledgetracker;

import java.util.List;

/**
 *
 * @author leverett
 */
public interface IDAL {
    
    boolean init();

    boolean saveData(DAL.Pledge pledge);

    List<DAL.Pledge> getData();

    void close();    
}

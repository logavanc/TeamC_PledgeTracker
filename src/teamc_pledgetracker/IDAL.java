package teamc_pledgetracker;

import java.util.List;

/**
 *
 * @author leverett
 */
public interface IDAL {
    
    boolean init();

    boolean saveData(Pledge pledge);

    List<Pledge> getData();

    void close();    
}

package teamc_pledgetracker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leverett
 */
public class MemoryDAL implements IDAL {

    private List<Pledge> m_pledges;
    
    @Override
    public boolean init() {
        m_pledges = new ArrayList<>();
        return true;
    }

    @Override
    public boolean saveData(Pledge pledge) {
        m_pledges.add(pledge);
        return true;
    }

    @Override
    public List<Pledge> getData() {
        return m_pledges;
    }

    @Override
    public void close() {
        m_pledges.clear();
        m_pledges = null;
    }
    
}

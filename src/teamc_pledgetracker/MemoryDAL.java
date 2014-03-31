package teamc_pledgetracker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leverett
 */
public class MemoryDAL implements IDAL {

    private List<DAL.Pledge> m_pledges;
    
    @Override
    public boolean init() {
        m_pledges = new ArrayList<>();
        return true;
    }

    @Override
    public boolean saveData(DAL.Pledge pledge) {
        m_pledges.add(pledge);
        return true;
    }

    @Override
    public List<DAL.Pledge> getData() {
        return m_pledges;
    }

    @Override
    public void close() {
        m_pledges.clear();
        m_pledges = null;
    }
    
}

package teamc_pledgetracker;

import java.util.List;

/**
 *
 * @author leverett
 */
public class DAL {
    public enum StorageType {
        MEMORY, FILE, DATABASE
    }
    private IDAL m_iDAL;
    private boolean bClosed = false;
    
    public DAL (){
        init(StorageType.MEMORY); //Default Week 1
    }
    public DAL (StorageType storageType) {
        init(storageType);
    }
    
    private void init(StorageType storageType) {
        switch (storageType) {
            case MEMORY:
                m_iDAL = new MemoryDAL();
                break;
            case FILE:
                m_iDAL = new FileDAL();
                break;
            case DATABASE:
                m_iDAL = new DatabaseDAL();
                break;
            default:
                //error
                break;
        }
        if (!m_iDAL.init()) {
            System.out.println("Unable to initialize DAL of type "+storageType.name());
        }
    }
    
    public boolean SavePledge(Pledge pledge)
    {
        m_iDAL.saveData(pledge);
        return true;
    }
    
    public List<Pledge> GetPledges(){
        return m_iDAL.getData();
    }
    
    public void close() {
        m_iDAL.close();
        m_iDAL = null;
        bClosed = true;
    }
    
    @Override
    protected void finalize() throws Throwable {
        try{
            if (!bClosed){
                close();
            }
        }
        catch(Throwable t){
            throw t;
        }finally{
            super.finalize();
        }
    }
}

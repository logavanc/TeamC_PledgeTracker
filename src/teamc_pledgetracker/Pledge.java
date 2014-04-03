package teamc_pledgetracker;

public class Pledge {

    private String Name;
    private String Charity;
    private int Pledgeamt;
    
    public String getName()
    {
        return Name;
    }
    
    public void setName (String name)
    {
        Name = name;
    }
    
    public String getCharity()
    {
        return Charity;
    }
    
    public void setCharity (String charity)
    {
        Charity = charity;
    }
    
    public int getPledgeamt()
    {
        return Pledgeamt;
    }
    
    public void setPledgeamt (int pledge)
    {
        Pledgeamt = pledge;
    }
    
    public Pledge(String name, String charity, int pledge)
    {
        Name = name;
        Charity = charity;
        Pledgeamt = pledge;
    }
   
}

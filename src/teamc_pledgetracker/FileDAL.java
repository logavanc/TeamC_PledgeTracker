package teamc_pledgetracker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author leverett
 */
public class FileDAL implements IDAL {

    private static final String mFile = "PledgeTracker.txt";
    private static final String mSeperator = ";";
            
    @Override
    public boolean init() {
        return true;
    }

    @Override
    public boolean saveData(Pledge pledge) {
        boolean bRet = true;
        try {
            addPledgeToFile(
                pledge.getName(),
                pledge.getCharity(),
                Integer.toString(pledge.getPledgeamt()));
        }
        catch (Exception e) {
            bRet = false;
        }
        return bRet;
    }

    @Override
    public List<Pledge> getData() {
        List<Pledge> Pledges = new ArrayList<>();
        File file = new File(mFile);
        try (Scanner inFile = new Scanner(file)) {
            while (inFile.hasNextLine()) {
                String[] sPledge = inFile.nextLine().split(mSeperator);
                Pledges.add(new Pledge(sPledge[0], sPledge[1], Integer.parseInt(sPledge[2])));
            }
            inFile.close();
        }
        catch (Exception e) {
            System.out.println("Unable to initialize file for reading. Exception: "+e.getMessage());
        }
        return Pledges;        
    }

    @Override
    public void close() {
    }
    
    private void addPledgeToFile(String sName, String Charity, String sAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append(sName).append(mSeperator).append(Charity).append(mSeperator).append(sAmount);
        try (PrintWriter file = new PrintWriter(new FileOutputStream(mFile, true))) {
            file.println(sb.toString());
            file.close();
        }
        catch (Exception e) {
            System.out.println("Exception saving Pledge to file: "+e.getMessage());
        }        
    }
    
}

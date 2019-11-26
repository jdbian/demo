import java.io.IOException;

public class demoProcessor {
    public static void main(String[] args){
        fileProcessor fp = new fileProcessor();
        //Testing to run command or windows batch file
        try{
            //if running in Windows system plz try folowing as test
            //fp.cmdRunner("./scripts/test.bat test1 test2 > ./scripts/result.txt");
            fp.cmdRunner("./scripts/test.sh test1 test2 > ./scripts/result.txt");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //Testing to replace table id in one ini file
        try{
            fp.replaceTableId("./input/demo.ini", "987654321");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Testing to replace table ids in all files under same dir
        try{
            fp.replaceAllTableId("./input", "213124354436", "5");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //Testing to set table ids in manually
        try{
            //fp.setTableId("876543");
            fp.replaceAllTableId("./input","5");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //Testing to set uniquekey manually
        try{
            fp.setUniqueKey("test111");
            fp.replaceAllTableId("./input", "213124354436", "5");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

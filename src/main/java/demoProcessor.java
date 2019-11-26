import java.io.IOException;

public class demoProcessor {
    public static void main(String[] args){
        fileProcessor fp = new fileProcessor();
        try{
            fp.cmdRunner("./scripts/test.sh test1 test2 > ./scripts/result.txt");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            fp.replaceTableId("./input/demo.ini", "987654321");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            fp.replaceAllTableId("./input", "213124354436", null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

import com.sun.istack.internal.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class fileProcessor{

    private String uniqueKey;
    private String tableId;

    public fileProcessor(){

    }

    public fileProcessor(String uniqueKey, String tableId){
        this.uniqueKey=uniqueKey;
        this.tableId=tableId;
    }

    /**
     *
     * @param cmdWithPath  this parameter has to be command/batch/exe file with absolute path, e.g. c:/Users/<username>/command.bat arg1 arg2 > result.txt
     * @throws IOException Throws IOexception if can not find the executable file
     */
    public void cmdRunner(String cmdWithPath) throws IOException {
        Runtime.getRuntime().exec(cmdWithPath);
    }

   public void replaceTableId(String file, String tableId) throws IOException {
        String key;
       String preStr = "8=";
        if (this.uniqueKey==null){
            key="# table id";
        }else{
            key = this.uniqueKey;
        }
        Path filepath = Paths.get(file);
        Stream<String> lines = Files.lines(filepath);
       // lines.forEach(System.out::println);
        List<String> replace = lines.collect(Collectors.toList());
        if (replace.contains(key)){
            replace.set(replace.indexOf(key)+1, preStr + tableId);
        }else{
            System.out.println("The file is not containing the required uniqueKey " + this.uniqueKey);
        }
        //System.out.println(replace);
        Files.write(filepath, replace);
        lines.close();
    }

    public void setUniqueKey(String uniqueKey){
        this.uniqueKey=uniqueKey;
    }


    public void replaceAllTableId(String dirpath, String tableId, @Nullable String waitTimeinSeconds) throws IOException {
        int wait;
        if (waitTimeinSeconds==null){
            wait=60;
        }else{
            wait=Integer.parseInt(waitTimeinSeconds);
        }
        Stream<Path> walk = Files.walk(Paths.get(dirpath));
        List<Path> iniFiles = walk.filter(Files::isRegularFile).map(file->file.toAbsolutePath()).collect(Collectors.toList());
        iniFiles.forEach(file -> {
            try {
                System.out.println("Starting to modify file " + file.toString());
                replaceTableId(file.toString(), tableId);
                TimeUnit.SECONDS.sleep(wait);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void setTableId(String tableId){
        this.tableId=tableId;
    }

    public void replaceAllTableId(String dirpath, @Nullable String waitTimeinSeconds) throws Exception {
        if (this.tableId!=null){
            replaceAllTableId(dirpath,this.tableId,waitTimeinSeconds);
        }else{
            System.out.println("The table id is Null, there is no value can be replaced");
            throw new Exception("The value of table id is null");
        }
    }

}

package DTC;
import fr.limsi.dctfinder.DCTExtractor;
import fr.limsi.dctfinder.DCTExtractorException;
import fr.limsi.dctfinder.PageInfo;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class main {


    public static void main(String[] args) {

        try {
            // File or Path to Wapiti binary file
            //File wapitiBinaryFile = new File("/Users/abdulrhmanalabrash/Downloads/wapiti-1.5.0/src/wapiti.c");
            Path wapitiBinaryFile = Paths.get("/usr/local/bin/wapiti");

            // Create DCT extractor
            DCTExtractor extractor = new DCTExtractor(wapitiBinaryFile);

            // Specify locale (can be Locale.US, Locale.UK, Locale.FRANCE, Locale.FRENCH, ...)
            Locale locale = Locale.ENGLISH;
            // Create URL
            //URL url = new URL('http://en.people.cn/90777/7804478.html');
            // Open inputstream from a downloaded file or directly from the URL.
            if (args.length<1) throw  new RuntimeException("Enter a valid parameter");
            String dirPath=args[0];
            if ( dirPath.isEmpty()){
                throw  new RuntimeException("Enter a valid parameter");
            }
            File aDirectory = new File(dirPath);
            // get a listing of all files in the directory
            String[] filesInDir = aDirectory.list();

            for (String link: filesInDir) {
                try{
                    if (link.startsWith(".")) continue;
                    Path path = Paths.get(dirPath, link);

                    // InputStream is = new FileInputStream(new File("/Users/abdulrhmanalabrash/Google Drive/Work/crawl_from_excel/pages/en.people.cn8177935.html.txt"));
                    InputStream is = new FileInputStream(new File(path.toString()));
                    // Get download date (Calendar object)
// Knowing download date will lead to better results,
//    but it can be set to null
                    Calendar downloadDate = new GregorianCalendar();

                    // Get page info
// the URL (second parameter) is used to detect a specific locale (e.g. UK), in case
//     a more general one is specified (e.g. ENGLISH)
// Specific locales are important, because ways to write dates can be very different
//     in different countries spaeking the same language (e.g. US versus UK)
// If we know in advance from which country the page is, specify the country.
// DCTFinder provides extraction rules for Locale.UK, Locale.US and Locale.FRENCH,
//     but we can specify your own rules
                    PageInfo pageInfo = extractor.getPageInfos(is, null, locale, downloadDate);

                    // Get download date
                    Calendar dctCalendar = pageInfo.getDCT();
                    Date calendarDate = dctCalendar.getTime();

                    // Get title
                    //String title = pageInfo.getTitle();
                    System.out.println(link+"\t"+calendarDate);
                }catch (NullPointerException e){
                    System.out.println("Exp with /t "+link);
                }
            }
        }catch (DCTExtractorException e ){
            System.out.println(e.getMessage()+"DCTExtractorException ");
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


}
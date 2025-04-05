import java.io.File;
import java.util.*;
//import edu.duke.*;

public class Tester {
	public static final String PATH=System.getProperty("user.dir")+"\\ALS_week4\\VigenereProgram\\data\\athens_keyflute.txt";
	public static final String MSG_PATH=System.getProperty("user.dir")+"\\ALS_week4\\VigenereProgram\\messages\\secretmessage4.txt";
	public static final String DIC_PATH=System.getProperty("user.dir")+"\\ALS_week4\\VigenereProgram\\";
	public void testTryKeyLength () {
		System.out.println("testTryKeyLength ------------------ "+PATH);
		System.out.println("testTryKeyLength ------------------ "+DIC_PATH);
		System.out.println("testTryKeyLength ------------------ "+MSG_PATH);
		FileResource fr = new FileResource(MSG_PATH);
		String input = fr.asString();
		VigenereBreaker vb = new VigenereBreaker();

		// make a new FileResource to read from the English dictionary file
        FileResource dicFr = new FileResource(DIC_PATH+"dictionaries/English");
        // use your readDictionary method to read the contents of that file into a HashSet of Strings
        HashSet<String> dic = vb.readDictionary(dicFr);
		HashMap<String,HashSet<String>>languages=new HashMap<>();
		File[] files = new File(DIC_PATH+"dictionaries/").listFiles();
//		System.err.println(files.length);
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
				languages.put(file.getName(),vb.readDictionary(new FileResource(file.getAbsolutePath())));
				}
			}
		}
		vb.breakForAllLangs(input,languages);
//		System.out.println(languages.keySet());
//		System.out.println(vb.breakForLanguage(input,dic));

//		int[] key = vb.tryKeyLength(input, 38,'c');
//		VigenereCipher vc = new VigenereCipher(key);
//        String currentDecryption = vc.decrypt(input);
//		System.out.println(currentDecryption);

//		int validWords = vb.countWords(currentDecryption, dic);
//        System.out.println("validWords: " + validWords);
//
//		for (int i = 0; i < key.length; i++) {
//		    System.out.print(key[i] + "\t");
//		}
//		System.out.println("\n");
	}

	public static void main(String[] args) {
		Tester test=new Tester();
		test.testTryKeyLength();
	}
}
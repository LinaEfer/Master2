import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileWorker {
	private static String fileName = "/home/m2eserv/frolkova/Documents/ECLE/SIR/12_19_16/Log-clients-themes.txt";
	private static List<String> Liste1;
	private static List<String> Liste2;
	private static List<String> prenoms;
	private static List<String> themes;
	private static int[][] array;
	
	public FileWorker(){
		this.prenoms = new ArrayList<String>();
		this.themes = new ArrayList<String>();
		this.Liste1 = new ArrayList<String>();
		this.Liste2 = new ArrayList<String>();
		this.array = new int[0][0]; 
	}
	public static void main(String[] args) throws FileNotFoundException {
		FileWorker fr = new FileWorker();
		fr.read(fileName);
		String textFromFile = FileWorker.read(fileName);
		//System.out.println(textFromFile);
		System.out.println(prenoms);
		System.out.println(themes);
		fr.createMatrix();
		fr.printMatrix();		
	}
	
	public static String read(String fileName) throws FileNotFoundException {
		File file = new File(fileName);	
	    StringBuilder sb = new StringBuilder();
	 
	    exists(fileName);
	 
	    try { 
	       	BufferedReader in = new BufferedReader(new FileReader((file).getAbsoluteFile()));
	        try {
	            String s;
	            while ((s = in.readLine()) != null) {
	                sb.append(s);
	                sb.append("\n");
	                int count=0;
	                //System.out.println(s);
	                String prenom="";
	                String theme="";
	                for (int i=0; i < s.length(); i++){
	                	if (s.charAt(i) == ';')
	                		count++;
	                	if (count == 1 && !(s.charAt(i) == ';'))
	                		prenom += s.charAt(i);
	                   	if (count == 2 && !(s.charAt(i) == ';'))
	                		theme += s.charAt(i);	                	
	               }
	               /*
	                * Add each element in the list with repetition
	                */
	               Liste1.add(prenom);
	               Liste2.add(theme);
	               
	               /*
	                * Add only one time the element in the list
	                */
	               if (!prenoms.contains(prenom))
	               	prenoms.add(prenom);
	               if (!themes.contains(theme))
	            	   themes.add(theme);
	            }
	            /*
	             * Initializing the matrix with real size
	             */
	            array = new int[prenoms.size()][themes.size()];
	            
	           
	        } finally {
	 	            in.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	    return sb.toString();
	}

	public void createMatrix(){
		for (int i = 0; i < Liste1.size(); i++){
			int x = prenoms.indexOf(Liste1.get(i));
			int y = themes.indexOf(Liste2.get(i));
			array[x][y] += 1;
		}
	}
	
	private static void exists(String fileName) throws FileNotFoundException {
	    File file = new File(fileName);
	    if (!file.exists()){
	        throw new FileNotFoundException(file.getName());
	    }
	}
	
	public void printMatrix(){
		for (int i = 0; i < array.length;i++){
			for (int j = 0; j < array[i].length; j++)
				System.out.print(array[i][j]+" ");
			System.out.println();
		}
	}
	
} 


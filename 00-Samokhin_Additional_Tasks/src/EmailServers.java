
public class EmailServers {

	public void mailWorker(String s) {
		int a = 0; // @ symbol indent
		int b = 0;// . symbol indent
		int c = 0; // symb position
		
		while(c<s.length()-1) {
			a = s.indexOf('@', c);
			b = s.indexOf('.', c);
			c = s.indexOf(';', c+1);
			
			System.out.println(s.substring(a+1, b));
		}
	}	
	public static void main(String[] args) {
		EmailServers emails = new EmailServers();
		emails.mailWorker("uu@yahoo.com; vasya@google.com; igor@mamboo.eu;");
		
		
	}
}
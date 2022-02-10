package helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Util {
	static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	static NumberFormat nf=new DecimalFormat("R$ #,##0.00",
			new DecimalFormatSymbols(new Locale("pt","BR")));
	
	public static String dateParaString(Date data) {
		return Util.sdf.format( data);
		
	}
	
	public static String doubleParaString(Double valor) {
		return Util.nf.format(valor);
		
	}
	
	public static Double stringParaDouble(String valor) {
		try {
			return (Double)Util.nf.parse(valor);
		}catch(ParseException e){
			return null;
			
		}
		
	}
	public static void pausar(int segundos) {
		try {
			TimeUnit.SECONDS.sleep(segundos);
		}catch(InterruptedException e){
			System.out.println("erro ao pausar por segundos");
		}
	}

}

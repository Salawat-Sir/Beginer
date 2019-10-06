// Программа КАЛЬКУЛЯТОР
//Разработал Сиразетдинов Салават
import java.io.*; //для ввода-вывода
import java.util.Arrays; //для проверки вхождения символов в массив
//import mCheck.*;
//import mRezult.*;

//import jdk.vm.ci.aarch64.AArch64.Flag;

class mCheck  { 
	String Str;    
	String Sign;
	boolean mArabFlag;
	boolean mRomanFlag;
	boolean mSignFlag;
	boolean mArabVal;
	String[] arab={"0", "1","2","3","4","5","6","7","8","9","10"};
	String[] roman={"I","II","III","IV","V","VI","VII","VIII","IX","X"};
	String[] romanW={"X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};
	String[] MathSign={"-","+","*","/"};

	// Являются ли числа римскими
	boolean CheckRoman() {	
		if (Arrays.asList(roman).contains(String.valueOf(Str.toUpperCase()))) {
			mRomanFlag = true;
			mArabFlag=false;
		}
		else mRomanFlag =false;
			
		return mRomanFlag;
	}
	// являются ли числа арабскими
	boolean CheckArab() {
 		if (Arrays.asList(arab).contains(String.valueOf(Str))) {
			mArabFlag = true; 
			mRomanFlag=false;
		}
		else mArabFlag=false;	
		return mArabFlag;
	}
	boolean CheckSign() {
		if (Arrays.asList(MathSign).contains(String.valueOf(Str))) {
			mSignFlag = true;
			
		}	
		else mSignFlag=false;
		return mSignFlag;
	}
	boolean ArabVal() {
		if (mArabFlag) {
			if ( Integer.parseInt(Str) <= 10 ) mArabVal=true;
			else mArabVal=false;
			}
	  	return mArabVal;
	}
	
}


class SolveIt {
	String Str1;
	String Str2;
	String Sign;
	int ResultArab;
	String ResultRoman;
	int Val1;
	int Val2;
	String[] roman={"I","II","III","IV","V","VI","VII","VIII","IX","X"};
	String[] romanW={"X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};
	//если цифры арабские
	int SolveArab() {
		switch (Sign) {
			case "-":
				ResultArab=Val1-Val2;
				break;
			case "+":
				ResultArab=Val1+Val2;
				break;
			case "/":
				ResultArab=Val1/Val2;
				break;
			case "*":
				ResultArab=Val1*Val2;
				break;
		} 
		return ResultArab;

	}
	// если цифры римские
	String SolveRoman() {
	// римские числа конвертируем в арабские и делаем вычисления
		Val1=Arrays.asList(roman).indexOf(Str1)+1;
		Val2=Arrays.asList(roman).indexOf(Str2)+1;
		if (Val1>0 & Val2>0) {
			ResultArab=SolveArab();
	//результат конвертируем в римское число 
		
			Val1=ResultArab/10;
			Val2=ResultArab % 10;
			if (Val1 != 0) Str1=romanW[Val1-1];
			else Str1="";
			if (Val2 != 0) Str2=roman[Val2-1];
			else Str2="";
			ResultRoman=Str1+Str2;
		}
		else {
			System.out.println("Ошибка - неверный ввод данных");
			System.exit(0);
		}
		 
//		String ResultRoman=Integer.toString(ResultArab);
		return ResultRoman;
	}
}


class Calculator4 {
	public static void main (String args[]) throws IOException {
        mCheck myCheck=new mCheck(); 
//		mCheck myCheckVal= new mCheck();
		SolveIt mySolve = new SolveIt();
		char c;
		String ValArab1="", ValRoman1="", Sign="";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Bвeдитe арабские либо римские числа не больше 10 или 'q' (или другой символ)  - для выхода.");
// Посимвольное считывание с консоли строку и проверка ввода чисел
		do {		
			c = (char) br.read();
			//(символ преобразуем в строку)
            myCheck.Str=String.valueOf(c);

			try {
				//если символ является арабской цифрой 
				if (myCheck.CheckArab() & ValRoman1 == "") {ValArab1=ValArab1+c; myCheck.Str=ValArab1; }  // Не смешивать арабские с римскими числами

				//если символы римские
				else if (myCheck.CheckRoman() & ValArab1 == "") {ValRoman1=ValRoman1+c; myCheck.Str=ValRoman1.toUpperCase();}    // Не смешивать римские с арабскими числами

				//если математический знак
				else if (myCheck.CheckSign()  & mySolve.Sign == null) {
					Sign ="" + c;
					mySolve.Sign=Sign;
					if (ValArab1 == "") {
						myCheck.Str=ValRoman1;
						mySolve.Str1=ValRoman1.toUpperCase(); 
					}
					else if (Integer.parseInt(ValArab1)>0 & Integer.parseInt(ValArab1)<=10) {
						myCheck.Str=ValArab1;
						mySolve.Val1=Integer.parseInt(ValArab1);
					}

					else
						{
						System.out.println("Ошибка - неверный ввод данных");
						System.exit(0);
						}	
					ValArab1="";
					ValRoman1="";
				} 
				
				else if (c == '\n') { /* Если достигли конца строки */
					if (ValArab1=="") {
						myCheck.Str=ValRoman1;
						mySolve.Str2=ValRoman1.toUpperCase();
						System.out.println("ответ: " + mySolve.SolveRoman());
					}
					else if (mySolve.Val1!=0 & Integer.parseInt(ValArab1) > 0 & Integer.parseInt(ValArab1)<=10) {
						myCheck.Str=ValArab1;
						mySolve.Val2=Integer.parseInt(ValArab1);
						System.out.println("ответ: " + mySolve.SolveArab());
					}

					else
						{
						System.out.println("Ошибка - неверный ввод данных");
						System.exit(0);
						}
					
					ValArab1="";
					ValRoman1="";
					Sign="";
					myCheck = null;
					myCheck =new mCheck();
					mySolve=null;
					mySolve = new SolveIt();
				}
				else if ( c == ' ') {
					/*Empty*/; //Пробел между символами не считать ошибкой
				}
				// если введены символы отличающиеся от арабских или римских цифр, то аварийный выход
//				else if (c != 'q') { 
//					System.out.println("Ошибка - неверный ввод данных");
//					System.exit(0);
//				}

			}
			catch (Error e) {
				System.out.println("Ошибка!!!!!!");
				System.exit(0);
			}
		} while(c != 'q');
	}
}

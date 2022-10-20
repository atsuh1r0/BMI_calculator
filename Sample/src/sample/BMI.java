package sample;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BMI {

	public static void main(String[] args) {

		// 開始メッセージ
		System.out.println( "※※BMI計算プログラムを開始します※※");
		System.out.println();

		// 変数定義
		double height = 0.0;		//身長(単位cm)を管理する変数
		double weight = 0.0;		//開始体重（単位kg）を管理する変数
		double bmi = 0.0;		//BMI値を管理する変数
		String inputHeight;
		String inputWeight;		
		Scanner sc = new Scanner(System.in);
//		Pattern patternHeight = Pattern.compile("^[0-9]+(.[0-9]+)?(cm)$");
		Pattern patternHeight = Pattern.compile("^[0-9]+(.[0-9]+)?(cm)");
//		Pattern patternWeight = Pattern.compile("^[0-9]+(.[0-9]+)?(kg)$");
		Pattern patternWeight = Pattern.compile("^[0-9]+(.[0-9]+)?(kg)");
		String unit;
		
		// 適切な値が入力されるまで繰り返す
		while(true) {
			// 身長を入力
			System.out.print("身長(単位cm)を入力してください>");
			inputHeight = sc.nextLine();
			unit = "cm";
			try {
				height = removeUnit(inputHeight, patternHeight, unit);
		    } catch(NumberFormatException e) {
		    	System.out.println("数値を入力してください。");
		    	continue;
		    }
			
			// 体重を入力
			System.out.print("体重(単位kg)を入力してください>");
			inputWeight = sc.nextLine();
			unit = "kg";
			try {
				weight = removeUnit(inputWeight, patternWeight, unit);
		    } catch(NumberFormatException e) {
		    	System.out.println("数値を入力してください。");
		    	continue;
		    }

			// 入力値チェック
			if(height <= 0.0 || weight <= 0.0) {
				// 0.0以下の数値が入力された場合は再入力を促す
				System.out.println("※※0以下の数値があるため、再入力してください※※");
				System.out.println();
				continue;
			}
			break;
		}

		// 体重、身長の画面出力
		System.out.println( "身長が " + height + "cm、体重が " + weight + "kgの場合");

		// BMIの計算
		bmi = calculateBMI(height, weight);

		// BMIの画面出力
		System.out.println( "BMIの値は " + bmi + "です。");

		// BMIに応じてメッセージを出力
		System.out.println(getMessage(bmi));

		// 終了メッセージ
		System.out.println();
		System.out.println( "※※BMI計算プログラムを終了します※※");

	}
	
	public static double removeUnit(String inputString, Pattern pattern, String unit) {
		int index = 0;
		var match = pattern.matcher(inputString);
    	if (match.find()) {
    		// 単位が入力された場合
    		index = inputString.indexOf(unit);
			if (index != -1) {
				inputString = inputString.substring(0, index);
			}
    	}
    	 return Double.parseDouble(inputString);
	}
	
	
	public static double calculateBMI(double height, double weight) {
		// cm --> mに変換（身長に0.01をかけて変換）
		height = height * 0.01;
		return (double)Math.round(weight / (height * height) * 100) / 100;
	}
	
	public static String getMessage(double bmi) {
		String msg;
		if(bmi >= 25.0) {
			// 25.0以上の場合
			msg = "太り気味です。";
			System.out.println();
		} else if(bmi < 18.5) {
			// 18.5未満の場合
			msg = "やせ気味です。";
		} else {
			// それ以外の場合
			msg = "正常値です。";
		}
		return msg;
	}

}

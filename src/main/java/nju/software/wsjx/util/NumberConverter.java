package nju.software.wsjx.util;

public class NumberConverter {

	public static void main(String[] args){
		System.out.println(convertFromChinese("��ǧ���Ԫ"));
	}
	public static int convertFromChinese (String chineseNumber){
		int result = 0;
		int temp = 1;//���һ����λ�������磺ʮ��
		int count = 0;//�ж��Ƿ���chArr
		char[] cnArr = new char[]{'һ','��','��','��','��','��','��','��','��'};
		char[] chArr = new char[]{'ʮ','��','ǧ','��','��'};
		for (int i = 0; i < chineseNumber.length(); i++) {
			boolean b = true;//�ж��Ƿ���chArr
			char c = chineseNumber.charAt(i);
			for (int j = 0; j < cnArr.length; j++) {//�ǵ�λ��������
				if (c == cnArr[j]) {
					if(0 != count){//������һ����λ֮ǰ���Ȱ���һ����λֵ���ӵ������
						result += temp;
						temp = 1;
						count = 0;
					}
					// �±�+1�����Ƕ�Ӧ��ֵ
					temp = j + 1;
					b = false;
					break;
				}
			}
			if(b){//��λ{'ʮ','��','ǧ','��','��'}
				for (int j = 0; j < chArr.length; j++) {
					if (c == chArr[j]) {
						switch (j) {
						case 0:
							temp *= 10;
							break;
						case 1:
							temp *= 100;
							break;
						case 2:
							temp *= 1000;
							break;
						case 3:
							temp *= 10000;
							break;
						case 4:
							temp *= 100000000;
							break;
						default:
							break;
						}
						count++;
					}
				}
			}
			if (i == chineseNumber.length() - 1) {//���������һ���ַ�
				result += temp;
			}
		}
		return result;
	}
}

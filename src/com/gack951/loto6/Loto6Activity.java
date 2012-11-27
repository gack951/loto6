package com.gack951.loto6;

import com.gack951.loto6.R;

import java.security.SecureRandom;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class Loto6Activity extends Activity implements OnClickListener{

	private Button button1;
	Random rnd=new SecureRandom();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button1=(Button)findViewById(R.id.button1);
		button1.setOnClickListener(this);
		
	}

	public void onClick(View v){
		TextView textview1=(TextView)findViewById(R.id.textview1);
		TextView textview2=(TextView)findViewById(R.id.textview2);
		EditText edit1=(EditText)findViewById(R.id.edit1);
		int max_number=0;
		int[] num_mem;
		
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edit1.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
		textview1.setText("");
		textview2.setText("");

		try{
			max_number=Integer.parseInt(edit1.getText().toString());
		}catch(NumberFormatException e){
			max_number=43;
			edit1.setText("43");
		}
		if(max_number<2||max_number>99999){
			max_number=43;
			edit1.setText("43");
		}

		int num_mem_len=(max_number<12 ? max_number : 12); /*ある数まで表示する*/
		num_mem_len=12;
		num_mem = new int[num_mem_len];

		if(max_number<12){
			for(int i = 0;i</*max_number*/12;i++){ //今は固定
				num_mem[i]=rnd.nextInt(max_number)+1;
				if(num_mem[i]==37||num_mem[i]==36){
					num_mem[i]-=rnd.nextInt(2)*27;
				}
			}
			num_mem_len=12;
		}else{
		for(int i = 0;i<num_mem_len;i++){
			num_mem[i]=rnd.nextInt(max_number)+1;
			if(num_mem[i]==37||num_mem[i]==36){
				num_mem[i]-=rnd.nextInt(2)*27;
			}
			for(int j = i;j>=0;j--){
				if(num_mem[j]==num_mem[i]){
					num_mem[i]=rnd.nextInt(max_number)+1;
					if(num_mem[i]==37||num_mem[i]==36){
						num_mem[i]-=rnd.nextInt(2)*27;
					}
					j=i;
					continue;
				}
			}
		}
		}

		for(int i = 0;i<num_mem_len/2;i++){
			textview1.append(Integer.toString(num_mem[i])+"\n");
			textview2.append(Integer.toString(num_mem[i+num_mem_len/2])+"\n");
		}
		if(num_mem_len%2==1){
			textview1.append(Integer.toString(num_mem[num_mem_len-1])+"\n");
		}
	}
}

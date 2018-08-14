package com.example.pink_police;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Verifysafe extends BaseAdapter{
		private Context context;
		ArrayList<String> a;
		ArrayList<String> b;
		ArrayList<String> c;
		

		public Verifysafe (Context applicationContext, ArrayList<String> x, ArrayList<String> y,ArrayList<String> z) {
			// TODO Auto-generated constructor stub
			this.context=applicationContext;
			this.a=x;
			this.b=y;
			this.c=z;
						
		


		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return a.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getItemViewType(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertview, ViewGroup parent) {
			// TODO Auto-generated method stub
	LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View gridView;
			if(convertview==null)
			{
				gridView=new View(context);
				gridView=inflator.inflate(R.layout.activity_verifysafe, null);
				
			}
			else
			{
				gridView=(View)convertview;
				
			}
			TextView tv1=(TextView)gridView.findViewById(R.id.textView1);
			TextView tv2=(TextView)gridView.findViewById(R.id.textView2);
			TextView tv3=(TextView)gridView.findViewById(R.id.textView3);
			TextView tv4=(TextView)gridView.findViewById(R.id.textView4);
			
					
			
			tv1.setText(a.get(position));
			tv2.setText(b.get(position));
			tv3.setText(c.get(position));
			tv4.setText("Verify");
			
			tv1.setTextColor(Color.BLACK);
			tv2.setTextColor(Color.BLACK);
			tv3.setTextColor(Color.BLACK);
			tv4.setTextColor(Color.BLUE);
			
		

			
			
			
			return gridView;
		
		}


	




	

}

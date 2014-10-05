package com.example.coen268_project;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class Budget extends Activity {

	 /** Called when the activity is first created. */
    
	private ItemsDB db;
	Cursor cost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_budget);		
		//WebView googleChartView = (WebView) findViewById(R.id.webView1);
		WebView googleChartView = new WebView(this);
		setContentView(googleChartView);	
		//Code to get values of cost of each item from db		
		
		//String jcost = getItemTotalCost("Jewellery");
		//Log.d("MY_DEBUG3","jcost="+jcost);
		Double jewellery_cost_doub = getItemTotalCost("Jewellery");
		Double outfit_cost_doub = getItemTotalCost("Outfits");
		Double hall_cost_doub = getItemTotalCost("Wedding Hall");
		Double food_cost_doub = getItemTotalCost("Food");
		Double deco_cost_doub = getItemTotalCost("Decoration");
		
		Double all_items_cost = jewellery_cost_doub+outfit_cost_doub+hall_cost_doub+food_cost_doub+deco_cost_doub;
		
		String jewellery_cost = Double.toString(jewellery_cost_doub/all_items_cost)+",";
		String outfit_cost = Double.toString(outfit_cost_doub/all_items_cost)+",";
		String hall_cost = Double.toString(hall_cost_doub/all_items_cost)+",";
		String food_cost = Double.toString(food_cost_doub/all_items_cost)+",";
		String deco_cost = Double.toString(deco_cost_doub/all_items_cost);
			
		Log.d ("MY_DBG", "J Cost="+jewellery_cost+outfit_cost+hall_cost+food_cost+deco_cost);
		Log.d ("MY_DBG", "O Cost="+outfit_cost);
		
		String chartData = "chd=t:"+jewellery_cost+outfit_cost+hall_cost+food_cost+deco_cost;
		//String chartData = "chd=t:11400.0,565.0,0.0,0.0,0.0";
		//String chartData = "chd=t:30.0,60.0,10.0,10.0,10.0";
		String chartLegend = "chl=Jewellery|Outfits|Wedding Hall|Food|Decoration";		
		String mUrl = "http://chart.apis.google.com/chart?cht=p3&chco=80C65A,224499,FF0000&chs=375x150&"+chartData+"&"+chartLegend;
		googleChartView.loadUrl(mUrl);		
		

	}
	
	@Override
	protected void onResume() {

	   super.onResume();
	   this.onCreate(null);
	}   
	
	public Double getItemTotalCost(String item_name)
	{
		Double total_cost=0.0;
		db = new ItemsDB(this);
		cost= db.getSubItems(item_name, "COST");
		//Log.d("MY_DEBUG4", "cost="+cost.getString(cost.getColumnIndex("COST")));
		
		if ((cost != null) && (cost.getCount() > 0))
		{
			total_cost+=Double.parseDouble(cost.getString(cost.getColumnIndex("COST")));
		}
		while(cost.moveToNext())
		{
			//Log.d("MY_DEBUG5", "cost="+cost.getString(cost.getColumnIndex("COST")));
			total_cost+=Double.parseDouble(cost.getString(cost.getColumnIndex("COST")));
		}
		//Log.d("MY_DEBUG4", "item="+item_name);
		Log.d("MY_DEBUG4", "total cost="+Double.toString(total_cost));
		return total_cost;
	}

}

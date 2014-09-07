package br.com.kpc.bandeiras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.message.BufferedHeader;

import android.renderscript.Script.FieldBase;
import android.support.v7.app.ActionBarActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Bandeiras extends ActionBarActivity implements Runnable {

	private TextView tvUrl;
	private ImageView imgBandeira;
	private Thread therad;
	String urlimg;
	Drawable imgTemp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bandeiras);

		tvUrl = (TextView) findViewById(R.id.tvURL);
		imgBandeira = (ImageView) findViewById(R.id.imgBandeira);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bandeiras, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void btConsumirWs(View v) {
		tvUrl.setText("Iniciando");
		
		therad = new Thread(Bandeiras.this);
		therad.start();
		
		tvUrl.setText(urlimg);
		imgBandeira.setImageDrawable(imgTemp);
		imgBandeira.setVisibility(0);
	}

	@Override
	public void run() {

		Log.d("Rum", "Entrouu");

		try {
			URL url = new URL(
					"http://177.130.172.150:8080/WSBandeira/rest/wsbandeira/getBandeira/KP");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));

			urlimg = in.readLine();
			
			InputStream is = (InputStream) new java.net.URL(urlimg)
					.getContent();
			imgTemp = Drawable.createFromStream(is, "src name");



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
